package com.eggy.movieapp.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieListResponse(


    @field:SerializedName("total_results")
    val totalResults: Int? = null,

    @field:SerializedName("results")
    val results: List<MovieItem>,

    ) : Parcelable
