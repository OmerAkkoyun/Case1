package com.example.casemovieapp.data.repository

import com.example.casemovieapp.data.model.MovieDetailsDTO
import com.example.casemovieapp.data.model.MovieListDTO
import com.example.casemovieapp.data.remote.ITheMovieApi
import com.example.casemovieapp.domain.repository.IDomainTheMovieRepository
import javax.inject.Inject

class TheMovieRepositoryImpl @Inject constructor(private val theMovieApi: ITheMovieApi) : IDomainTheMovieRepository{

    override suspend fun getNowPlayingMovies(): MovieListDTO {
      return  theMovieApi.getNowPlayingMovies()
    }

    override suspend fun getUpComingMovies(): MovieListDTO {
        return  theMovieApi.getUpComingMovies()
    }

    override suspend fun getMovieDetailsById(movieId: String): MovieDetailsDTO {
        return theMovieApi.getMovieDetailsById(movieId)
    }


}