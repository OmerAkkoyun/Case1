package com.example.casemovieapp.domain.repository

import com.example.casemovieapp.data.model.MovieDetailsDTO
import com.example.casemovieapp.data.model.MovieListDTO
import retrofit2.http.Path

interface IDomainTheMovieRepository {

    suspend fun getNowPlayingMovies() : MovieListDTO
    suspend fun getUpComingMovies() : MovieListDTO
    suspend fun getMovieDetailsById(movieId:String) : MovieDetailsDTO

}