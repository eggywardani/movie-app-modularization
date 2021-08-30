package com.eggy.movieapp.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eggy.movieapp.core.domain.usecase.MoviesUseCase

class MovieViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val movies = moviesUseCase.getAllMovie().asLiveData()
}