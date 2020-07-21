package com.example.basemvvm.model
import com.google.gson.annotations.SerializedName


/****************************************************
 * Copyright (C) Alan Corporation. All rights reserved.
 *
 * Author: AlanLai
 * Create Date: 2020/7/21
 * Usage:
 *
 * Revision History
 * Date         Author           Description
 ****************************************************/

class test : ArrayList<test.testItem>(){
    data class testItem(
        @SerializedName("addr")
        val mAddr: String,
        @SerializedName("busiMemo")
        val mBusiMemo: String,
        @SerializedName("busiTime")
        val mBusiTime: String,
        @SerializedName("hsnCd")
        val mHsnCd: String,
        @SerializedName("hsnNm")
        val mHsnNm: String,
        @SerializedName("latitude")
        val mLatitude: String,
        @SerializedName("longitude")
        val mLongitude: String,
        @SerializedName("storeCd")
        val mStoreCd: String,
        @SerializedName("storeNm")
        val mStoreNm: String,
        @SerializedName("tel")
        val mTel: String,
        @SerializedName("total")
        val mTotal: String,
        @SerializedName("townCd")
        val mTownCd: Any,
        @SerializedName("townNm")
        val mTownNm: String,
        @SerializedName("updateTime")
        val mUpdateTime: String,
        @SerializedName("zipCd")
        val mZipCd: String
    )
}