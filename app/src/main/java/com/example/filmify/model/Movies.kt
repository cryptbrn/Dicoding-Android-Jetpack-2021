package com.example.filmify.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "movies"
)

data class Movies(
    @PrimaryKey
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