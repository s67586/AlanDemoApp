package com.example.basemvvm.network

import com.example.basemvvm.BuildConfig
import com.example.basemvvm.model.test
import com.example.basemvvm.util.ALogger
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.IllegalArgumentException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

object Client {

    val apiService: ApiService
    private const val MESSAGE_KEY = "message"
    private const val ERROR_KEY = "error"

    init {
        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor {
                    val requestBuilder = it.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .url(it.request().url)
                    it.proceed(requestBuilder.build())
                }
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(logInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend inline fun <reified T> safeApiCall(crossinline callFunction: suspend () -> Response<T>): BaseResponse<T> =
            withContext(Dispatchers.IO) {
        try {
            val response = callFunction.invoke()
            if (response.isSuccessful) {
                if (response.body() == null) {
                    if (T::class.java.simpleName == Unit.javaClass.simpleName)
                        BaseResponse.Success(response.body() as T)
                    else
                        BaseResponse.Error(IllegalArgumentException("content error"))
                } else
                    BaseResponse.Success(response.body()!!)
            } else
                BaseResponse.Error(HttpException(response))
        } catch (e: Exception) {
            ALogger.instance.logError("Exception =  ${getErrorType(e)}")
            BaseResponse.Error(e)
        }
    }

    fun getErrorType(e: Exception): NetworkErrorMassage {
        return when (e) {
            is HttpException -> {
                val body = e.response()?.errorBody()
                NetworkErrorMassage(ErrorType.HTTP, getErrorMessage(body))
            }
            is SocketTimeoutException -> NetworkErrorMassage(ErrorType.TIMEOUT)
            is IOException -> NetworkErrorMassage(ErrorType.IO)
            else -> NetworkErrorMassage(ErrorType.UNKNOWN)
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }
}