package com.example.basemvvm.util

/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/5/14
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

enum class DateFormatType(val format: String) {
    format1("yyyy-MM-dd"),
    format2("yyyy-MM-dd HH:mm:ss.SSS"),
    format3("yyyy/MM/dd HH:mm"),
    format5("yyyy/MM/dd"),
    format6("HH:mm"),
    format7("HH:mm:ss"),
    format8("HHmmss"),
    format9("mm:ss"),
    format10("yyyy.MM.dd"),
    format11("yyyyMMddHHmm"),
    format12("yyyyMMdd"),
    format13("yyyy"),
    format14("MM"),
    format15("dd"),
    format17("yyyyMMddHHmmss"),
    format18("yyyy/MM/dd HH:mm:ss"),
    format19("MMM dd, yyyy\nhh:mm aa");
}