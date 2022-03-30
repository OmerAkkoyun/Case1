package com.example.casemovieapp.data.remote

import com.example.casemovieapp.common.Constants
import com.example.casemovieapp.data.model.MovieDetailsDTO
import com.example.casemovieapp.data.model.MovieListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ITheMovieApi {


    @GET ("now_playing?api_key=${Constants.API_KEY}")
    suspend fun getNowPlayingMovies() : MovieListDTO

    @GET ("upcoming?api_key=${Constants.API_KEY}")
    suspend fun getUpComingMovies() : MovieListDTO


    @GET ("{movie_id}?api_key=${Constants.API_KEY}")
    suspend fun getMovieDetailsById(@Path("movie_id") movie_id:String) : MovieDetailsDTO

}