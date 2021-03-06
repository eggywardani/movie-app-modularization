package com.eggy.movieapp.core.domain.usecase

import com.eggy.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {

    fun getAllMovie(): Flow<com.eggy.movieapp.core.data.Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)

}