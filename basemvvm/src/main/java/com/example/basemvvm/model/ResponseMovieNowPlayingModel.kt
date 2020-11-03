package com.example.basemvvm.model


import com.google.gson.annotations.SerializedName

data class ResponseMovieNowPlayingModel(
    @SerializedName("dates")
    val mDates: Dates,
    @SerializedName("page")
    val mPage: Int,
    @SerializedName("results")
    val mResults: List<Result>,
    @SerializedName("total_pages")
    val mTotalPages: Int,
    @SerializedName("total_results")
    val mTotalResults: Int
) {
    data class Dates(
        @SerializedName("maximum")
        val mMaximum: String,
        @SerializedName("minimum")
        val mMinimum: String
    )

    data class Result(
        @SerializedName("adult")
        val mAdult: Boolean,
        @SerializedName("backdrop_path")
        val mBackdropPath: String,
        @SerializedName("genre_ids")
        val mGenreIds: List<Int>,
        @SerializedName("id")
        val mId: Int,
        @SerializedName("original_language")
        val mOriginalLanguage: String,
        @SerializedName("original_title")
        val mOriginalTitle: String,
        @SerializedName("overview")
        val mOverview: String,
        @SerializedName("popularity")
        val mPopularity: Double,
        @SerializedName("poster_path")
        val mPosterPath: String,
        @SerializedName("release_date")
        val mReleaseDate: String,
        @SerializedName("title")
        val mTitle: String,
        @SerializedName("video")
        val mVideo: Boolean,
        @SerializedName("vote_average")
        val mVoteAverage: Double,
        @SerializedName("vote_count")
        val mVoteCount: Int
    )
}