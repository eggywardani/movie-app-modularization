package com.eggy.movieapp.detail

import androidx.lifecycle.ViewModel
import com.eggy.movieapp.core.domain.model.Movie
import com.eggy.movieapp.core.domain.usecase.MoviesUseCase

class DetailViewModel(private val moviesUseCase: MoviesUseCase):ViewModel() {
    fun setFavoriteMovie(movie: Movie, state:Boolean) =
        moviesUseCase.setFavoriteMovie(movie, state)
}