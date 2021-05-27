package com.example.filmify.data.remote


object ApiResponse {
    data class Result(
        var success: Boolean,
        val results: List<MoviesResponse>?,
    )

    data class MoviesResponse(
        val id: Int,
        val original_language: String,
        val overview: String,
        val poster_path: String,
        val release_date: String?,
        val first_air_date: String?,
        val original_name: String?,
        val original_title: String?,
        val title: String?,
        val name: String?,
        val vote_average: Double,
    )
}
