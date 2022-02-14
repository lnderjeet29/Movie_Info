package com.example.movieinfo.Repo

import com.example.movieinfo.model.Movie
import com.example.movieinfo.model.TrendingMovie
import com.example.movieinfo.model.Video

object Repository {
    private val apiServie= Network.getApiService()

    suspend fun getMovieDetail(movieId:String):Movie{
        return apiServie.getMovieDetail(movieId, Network.Apikey)
    }
    suspend fun getAllMovieData(media_type:String,time_window:String):TrendingMovie{
        return apiServie.getAllTrendingMovie(media_type,time_window,Network.Apikey)
    }
    suspend fun getMovieVideo(movieId: String):Video{
        return apiServie.getMovieVedio(movieId,Network.Apikey)
    }
    suspend fun getUpcomingMovie():TrendingMovie{
        return apiServie.getUpcomingMovie(Network.Apikey)
    }
    suspend fun getTopRated():TrendingMovie{
        return apiServie.getTopRated(Network.Apikey)
    }
    suspend fun getPopularSeries():TrendingMovie{
        return apiServie.getPopularSeries(Network.Apikey)
    }
    suspend fun getSearchMovie(query:String):TrendingMovie{
        return apiServie.getSearchMovie(Network.Apikey,query)
    }
}