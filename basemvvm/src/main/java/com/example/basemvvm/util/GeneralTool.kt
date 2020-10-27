package com.example.basemvvm.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.LocationManager
import android.util.DisplayMetrics
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.basemvvm.base.BaseApplication
import java.util.*


/****************************************************
 * Author: AlanLai
 * Create Date: 2020/5/3
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class GeneralTool {

    private var oldMsg: String? = null
    private var time: Long = 0

    fun showToast(
        context: Context?,
        msg: String,
        duration: Int
    ) {
        if (msg != oldMsg) { // 當顯示的內容不一樣時，即斷定為不是同一個Toast
            val toast = Toast.makeText(context, msg, duration)
            val viewId =
                Resources.getSystem().getIdentifier("message", "id", "android")
            val textView = toast.view.findViewById<TextView>(viewId)
            textView.textSize = 18f
            toast.show()
            time = System.currentTimeMillis()
        } else {
            // 顯示內容一樣時，只有間隔時間大於2秒時才顯示
            if (System.currentTimeMillis() - time > 2000) {
                val toast = Toast.makeText(context, msg, duration)
                val viewId = Resources.getSystem()
                    .getIdentifier("message", "id", "android")
                val textView = toast.view.findViewById<TextView>(viewId)
                textView.textSize = 18f
                toast.show()
                time = System.currentTimeMillis()
            }
        }
        oldMsg = msg
    }

    fun checkGPSPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                BaseApplication.instance().applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                BaseApplication.instance().applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    fun checkCameraPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                BaseApplication.instance().applicationContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    fun requestPermission(
        activity: Activity,
        requests: List<String>
    ) {
        val requestList: MutableList<String> =
            ArrayList()
        for (permission in requests) {
            if (!isPermissionGranted(
                    permission
                )
            ) {
            }
            if (isDenied(
                    permission
                )
            ) {
                requestList.add(permission)
            }
        }
        val permissionArray = requestList.toTypedArray()
        ActivityCompat.requestPermissions(activity, permissionArray, Pub.REQUEST_PERMISSION)
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            BaseApplication.instance().applicationContext,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isDenied(
        permission: String
    ): Boolean {
        return ActivityCompat.checkSelfPermission(
            BaseApplication.instance().applicationContext,
            permission
        ) == PackageManager.PERMISSION_DENIED
    }

    fun isGPSEnable(activity: Activity): Boolean {
        val locationManager: LocationManager =
            activity.getSystemService(Activity.LOCATION_SERVICE) as LocationManager
        val gpsEnable: Boolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (gpsEnable) {
            return true
        }
        return false
    }

    fun dpToPx(context: Context, dp: Float): Int {
        return (dp * context.resources
            .displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT).toInt()
    }

    /**
     * @return 本地版號
     */
    fun getLocalVersion(ctx: Context): Int {
        var localVersion = 0
        try {
            val packageInfo = ctx.applicationContext
                .packageManager
                .getPackageInfo(ctx.packageName, 0)
            localVersion = packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return localVersion
    }

    /**
     * @return 本地版號名稱
     */
    fun getLocalVersionName(ctx: Context): String? {
        var localVersion = ""
        try {
            val packageInfo = ctx.applicationContext
                .packageManager
                .getPackageInfo(ctx.packageName, 0)
            localVersion = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return localVersion
    }
}