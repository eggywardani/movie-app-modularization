package com.eggy.movieapp.core.domain.repository

import com.eggy.movieapp.core.data.Resource
import com.eggy.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies():Flow<Resource<List<Movie>>>
    fun getFavoriteMovies():Flow<List<Movie>>
    fun  setFavorite(movie: Movie, state:Boolean)
}