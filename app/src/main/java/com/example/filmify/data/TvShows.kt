package com.example.filmify.data

class TvShows {
    data class TvShows(
            var tvShowId:String,
            var title: String,
            var description: String,
            var releaseYear: String,
            var rating: String,
            var status: String,
            var originalLanguage: String,
            var imagePath: String,
    )
}