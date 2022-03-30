package com.example.casemovieapp.data.model

import com.example.casemovieapp.domain.model.Movie

data class MovieListDTO(
    val dates: DatesDTO,
    val page: Int,
    val results: List<MovieDTO>,
    val total_pages: Int,
    val total_results: Int
)

fun MovieListDTO.toDomainMovieList(): List<Movie> {
    return results.map { it.toDomainMovie() }
}



