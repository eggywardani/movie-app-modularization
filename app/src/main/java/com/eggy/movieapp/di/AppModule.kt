package com.eggy.movieapp.di

import com.eggy.movieapp.core.domain.usecase.MoviesInteractor
import com.eggy.movieapp.core.domain.usecase.MoviesUseCase
import com.eggy.movieapp.detail.DetailViewModel
import com.eggy.movieapp.favorite.FavoriteViewModel
import com.eggy.movieapp.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory <MoviesUseCase>  {
        MoviesInteractor(get())
    }


}

val viewModelModule = module {
    viewModel {
        MovieViewModel(get())
    }

    viewModel {
        FavoriteViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }
}