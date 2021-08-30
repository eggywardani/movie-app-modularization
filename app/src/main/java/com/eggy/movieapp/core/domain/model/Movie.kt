package com.eggy.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(

    val idMovie: Int,

    val originalTitle: String,

    val overview: String,

    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,

    var isFavorite: Boolean = false
) : Parcelable