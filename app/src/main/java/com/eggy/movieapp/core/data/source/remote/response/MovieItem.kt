package com.eggy.movieapp.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieItem(
    @field:SerializedName("id")
    val id: Int? = null,


    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,


    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,


    @field:SerializedName("vote_average")
    val voteAverage: Double? = null
):Parcelable