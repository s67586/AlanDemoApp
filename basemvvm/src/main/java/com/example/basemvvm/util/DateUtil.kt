package com.example.basemvvm.util

import java.text.SimpleDateFormat
import java.util.*

/****************************************************
 * Author: AlanLai
 * Create Date: 2020/5/6
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

object DateUtil {

    fun getNowDateTime(formatType: DateFormatType): String {
        val sdf = SimpleDateFormat(formatType.format)
        return sdf.format(Date())
    }


    fun getNowTime(formatType: DateFormatType): String {
        val sdf = SimpleDateFormat(formatType.format)
        return sdf.format(Date())
    }


    fun getNowTimeDetail(formatType: DateFormatType): String {
        val sdf = SimpleDateFormat(formatType.format)
        return sdf.format(Date())
    }

    @JvmStatic
    fun stampToDate(time: Long, formatType: DateFormatType, locale: Locale): String {

        val simpleDateFormat = SimpleDateFormat(formatType.format, locale)

        return simpleDateFormat.format(Date(time * 1000))
    }

}