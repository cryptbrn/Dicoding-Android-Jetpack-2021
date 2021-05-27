package com.example.filmify.data

class Movies {
    data class Movie(
            var id:String,
            var title: String,
            var description: String,
            var releaseYear: String,
            var rating: String,
            var status: String,
            var originalLanguage: String,
            var imagePath: String,
    )
}