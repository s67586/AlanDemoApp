package com.example.basemvvm.util

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.example.basemvvm.base.BaseApplication

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/4/30
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class SharedPreferenceManager {

    private fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.instance())
    }

    fun getString(key: String?): String? {
        return if (TextUtils.isEmpty(key)) {
            ""
        } else getString(key, "")
    }

    fun getString(key: String?, defValue: String?): String? {
        if (TextUtils.isEmpty(key)) {
            return ""
        }
        val sp = getPreferences()
        return sp.getString(key, defValue)
    }

    @Synchronized
    fun setString(key: String?, value: String?) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val editor = getPreferences().edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getInt(key: String?): Int {
        return if (TextUtils.isEmpty(key)) {
            -1
        } else getInt(key, -1)
    }

    fun getInt(key: String?, defValue: Int): Int {
        if (TextUtils.isEmpty(key)) {
            return -1
        }
        val sp = getPreferences()
        return sp.getInt(key, defValue)
    }

    fun setInt(key: String?, value: Int) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val editor = getPreferences().edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return if (TextUtils.isEmpty(key)) {
            false
        } else getBoolean(key, false)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        if (TextUtils.isEmpty(key)) {
            return false
        }
        val sp = getPreferences()
        val result = sp.getBoolean(key, defValue)
        return result
    }

    fun setBoolean(key: String, value: Boolean) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val editor = getPreferences().edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun setLong(key: String, value: Long) {
        if (TextUtils.isEmpty(key)) {
            return
        }
        val editor = getPreferences().edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String, defValue: Long): Long {
        if (TextUtils.isEmpty(key)) {
            return defValue
        }
        val sp = getPreferences()
        val result = sp.getLong(key, defValue)
        return result
    }

    fun setBytes(key: String?, value: ByteArray?) {
        if (value == null) {
            return
        }
        val editor = getPreferences().edit()
        editor.putString(key, String(value))
        editor.apply()
    }

    fun getBytes(key: String): ByteArray? {
        if (TextUtils.isEmpty(key)) {
            return null
        }
        val sp = getPreferences()
        val result = sp.getString(key, "")
        return result!!.toByteArray()
    }
}