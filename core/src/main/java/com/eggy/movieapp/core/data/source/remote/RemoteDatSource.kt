package com.eggy.movieapp.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.eggy.movieapp.core.data.source.remote.network.ApiResponse
import com.eggy.movieapp.core.data.source.remote.network.ApiService
import com.eggy.movieapp.core.data.source.remote.response.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDatSource(private val apiService: ApiService) {


    @SuppressLint("CheckResult")
    fun getAllMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return flow {
            try {
                val response =  apiService.getList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))

                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}