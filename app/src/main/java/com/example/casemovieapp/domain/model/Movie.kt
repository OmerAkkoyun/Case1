package com.example.casemovieapp.domain.model

data class Movie(
    val movie_id: String,
    val movie_title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String?,
    val backdrop_path: String?

)

