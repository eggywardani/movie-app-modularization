package com.eggy.movieapp.core.domain.usecase

import com.eggy.movieapp.core.data.Resource
import com.eggy.movieapp.core.domain.model.Movie
import com.eggy.movieapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor (private val movieRepository: IMovieRepository): MoviesUseCase{
    override fun getAllMovie(): Flow<com.eggy.movieapp.core.data.Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()
    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        movieRepository.setFavorite(movie, state)
    }


}