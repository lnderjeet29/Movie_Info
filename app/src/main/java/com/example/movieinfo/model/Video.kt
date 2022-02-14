package com.example.movieinfo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Video(
    val id: Int,
    val results: List<Result>
){
    @Parcelize
    data class Result(
        val id: String,
        val iso_3166_1: String,
        val iso_639_1: String,
        val key: String,
        val name: String,
        val official: Boolean,
        val published_at: String,
        val site: String,
        val size: Int,
        val type: String
    ): Parcelable
}