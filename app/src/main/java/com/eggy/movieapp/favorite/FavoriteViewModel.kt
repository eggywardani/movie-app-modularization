package com.eggy.movieapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.eggy.movieapp.core.domain.usecase.MoviesUseCase

class FavoriteViewModel(moviesUseCase: MoviesUseCase):ViewModel() {

    val movies = moviesUseCase.getFavoriteMovie().asLiveData()
}