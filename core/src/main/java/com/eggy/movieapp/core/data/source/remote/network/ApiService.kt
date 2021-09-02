package com.eggy.movieapp.core.data.source.remote.network

import com.eggy.movieapp.core.BuildConfig
import com.eggy.movieapp.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET

interface ApiService {


    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    suspend fun getList(): MovieListResponse
}