package com.example.filmify.model


object ApiResponse {
    data class Results(
        var success: Boolean,
        val results: List<Movies>?,
    )

    data class Result(
            var success: Boolean,
            val result: Movies?
    )
}
