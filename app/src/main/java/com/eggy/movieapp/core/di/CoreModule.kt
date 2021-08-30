package com.eggy.movieapp.core.di

import androidx.room.Room
import com.eggy.movieapp.BuildConfig
import com.eggy.movieapp.core.data.MovieRepository
import com.eggy.movieapp.core.data.source.local.LocalDataSource
import com.eggy.movieapp.core.data.source.local.room.MovieDatabase
import com.eggy.movieapp.core.data.source.remote.RemoteDatSource
import com.eggy.movieapp.core.data.source.remote.network.ApiService
import com.eggy.movieapp.core.domain.repository.IMovieRepository
import com.eggy.movieapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<MovieDatabase>().movieDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movies.db"

        ).fallbackToDestructiveMigration().build()
    }
}


val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDatSource(get())
    }
    factory {
        AppExecutors()
    }

    single<IMovieRepository> {
        MovieRepository(get(), get(), get())
    }
}