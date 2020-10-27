package com.example.basemvvm.base

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.basemvvm.BuildConfig
import com.facebook.stetho.Stetho
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.properties.Delegates

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/4/30
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class BaseApplication : Application() {

    companion object {
        private var instance: BaseApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // Stetho Init
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        getHashKey()
    }

    fun exitApp() {
        // Finish all activities.
        killProcess()
        finishAndRemoveAllTasks()
    }

    /**
     * Kill current process.
     */
    private fun killProcess() {
        val home = Intent(Intent.ACTION_MAIN)
        home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        home.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        home.addCategory(Intent.CATEGORY_HOME)
        startActivity(home)
    }

    private fun finishAndRemoveAllTasks() {
        val am =
            baseContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                ?: return
        try {
            val appTasks = am.appTasks
            for (appTask in appTasks) {
                //CqrLog.debug("will finish and remove task: id=" + appTask.getTaskInfo().id);
                appTask.finishAndRemoveTask()
            }
        } catch (e: SecurityException) {
            //CqrLog.debug(e);
        }
    }

    private fun getHashKey() {
        val info: PackageInfo
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val KeyResult = String(Base64.encode(md.digest(), 0)) //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("TAG", "hash key = $KeyResult")
                Toast.makeText(this, "My FB Key is \n$KeyResult", Toast.LENGTH_LONG).show()
            }
        } catch (e1: PackageManager.NameNotFoundException) {
            Log.e("TAG", "name not found$e1")
        } catch (e: NoSuchAlgorithmException) {
            Log.e("TAG", "no such an algorithm$e")
        } catch (e: Exception) {
            Log.e("TAG", "exception$e")
        }
    }
}