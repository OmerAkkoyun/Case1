package com.example.casemovieapp.data.model

import com.example.casemovieapp.domain.model.Movie

data class MovieDTO(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDTO.toDomainMovie(): Movie {
    return Movie(
        movie_id = id.toString(),
        movie_title = title,
        overview = overview,
        release_date = release_date,
        poster_path = poster_path,
        backdrop_path = backdrop_path
    )
}