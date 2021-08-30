package com.eggy.movieapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_movie")
    val idMovie: Int,

    @ColumnInfo(name = "original_title")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,



    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable
