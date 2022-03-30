package com.example.casemovieapp.domain.use_case

import com.example.casemovieapp.R
import com.example.casemovieapp.common.Resource
import com.example.casemovieapp.data.model.toDomainMovieDetails
import com.example.casemovieapp.data.model.toDomainMovieList
import com.example.casemovieapp.domain.model.Movie
import com.example.casemovieapp.domain.model.MovieDetails
import com.example.casemovieapp.domain.repository.IDomainTheMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TheMovieUseCase @Inject constructor(private val iDomainTheMovieRepository: IDomainTheMovieRepository){

     suspend fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> = flow {
        try {
            val movies = iDomainTheMovieRepository.getNowPlayingMovies().toDomainMovieList()

            // loading
            emit(Resource.Loading())
            emit(Resource.Success(movies))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage))
        }
    }


    suspend fun getUpComingMovies(): Flow<Resource<List<Movie>>> = flow {
        try {
            // loading
            emit(Resource.Loading())
            val movies = iDomainTheMovieRepository.getUpComingMovies().toDomainMovieList()
            emit(Resource.Success(movies))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage))
        }
    }


    suspend fun getMovieDetailsById(movieId:String): Flow<Resource<MovieDetails>> = flow {
        try {
            // loading
            emit(Resource.Loading())
            val movies = iDomainTheMovieRepository.getMovieDetailsById(movieId).toDomainMovieDetails()
            emit(Resource.Success(movies))

        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage))
        }catch (e: IOException){
            emit(Resource.Error(e.localizedMessage))
        }
    }


}