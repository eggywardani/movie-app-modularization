package com.eggy.movieapp.core.data

import com.eggy.movieapp.core.data.source.local.LocalDataSource
import com.eggy.movieapp.core.data.source.remote.RemoteDatSource
import com.eggy.movieapp.core.data.source.remote.network.ApiResponse
import com.eggy.movieapp.core.data.source.remote.response.MovieItem
import com.eggy.movieapp.core.domain.model.Movie
import com.eggy.movieapp.core.domain.repository.IMovieRepository
import com.eggy.movieapp.core.utils.AppExecutors
import com.eggy.movieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDatSource: RemoteDatSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDatSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()





    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

}