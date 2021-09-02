package com.eggy.movieapp.core.utils

import com.eggy.movieapp.core.data.source.local.entity.MovieEntity
import com.eggy.movieapp.core.data.source.remote.response.MovieItem
import com.eggy.movieapp.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                idMovie = it.id ?: 0,
                originalTitle = it.originalTitle!!,
                overview = it.overview!!,
                posterPath = it.posterPath!!,
                backdropPath = it.backdropPath!!,
                voteAverage = it.voteAverage!!,
                isFavorite = false

            )
            movieList.add(movie)
        }
        return movieList
    }


    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                idMovie = it.idMovie,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                isFavorite = it.isFavorite
            )
        }


    fun mapDomainToEntity(input: Movie): MovieEntity =
        MovieEntity(
            idMovie = input.idMovie,
            originalTitle = input.originalTitle,
            overview = input.overview,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            voteAverage = input.voteAverage,
            isFavorite = input.isFavorite
        )
}