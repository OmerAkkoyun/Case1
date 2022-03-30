package com.example.casemovieapp.data.model

import com.example.casemovieapp.domain.model.MovieDetails

data class MovieDetailsDTO(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<GenreDTO>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompanyDTO>,
    val production_countries: List<ProductionCountryDTO>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguageDTO>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailsDTO.toDomainMovieDetails(): MovieDetails {
    return MovieDetails(
        overview = overview,
        release_date = release_date,
        backdrop_path = backdrop_path,
        title = title,
        vote_average = vote_average,
        imdb_id = imdb_id
    )

}