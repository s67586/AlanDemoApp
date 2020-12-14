package com.example.basemvvm.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponsePhotosModel : ArrayList<ResponsePhotosModelItem>()

data class ResponsePhotosModelItem(
        @SerializedName("albumId")
        val mAlbumId: Int,
        @SerializedName("id")
        val mId: Int,
        @SerializedName("thumbnailUrl")
        val mThumbnailUrl: String,
        @SerializedName("title")
        val mTitle: String,
        @SerializedName("url")
        val mUrl: String
):Serializable