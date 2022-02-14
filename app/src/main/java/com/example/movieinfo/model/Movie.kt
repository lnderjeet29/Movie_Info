package com.example.movieinfo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
):Parcelable
{
    @Parcelize
    data class Genre(
        val id: Int,
        val name: String
    ):Parcelable

    @Parcelize
    data class BelongsToCollection(
        val backdrop_path: String,
        val id: Int,
        val name: String,
        val poster_path: String
    ):Parcelable
}