package com.example.movieinfo.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieinfo.Repo.Repository
import com.example.movieinfo.model.Movie
import com.example.movieinfo.model.TrendingMovie
import com.example.movieinfo.model.Video
import kotlinx.coroutines.launch

class MovieViewModel:ViewModel() {
    val MovieData=MutableLiveData<Movie>()
    val TrendingMovie=MutableLiveData<TrendingMovie>()
    val UpcomingMovie=MutableLiveData<TrendingMovie>()
    val topRatedMovie=MutableLiveData<TrendingMovie>()
    val PopularMovie=MutableLiveData<TrendingMovie>()
    val SearchMovie=MutableLiveData<TrendingMovie>()
    val movieVideo=MutableLiveData<Video>()
    fun getMovieDetails(movieId:String){
        viewModelScope.launch {
            try {
                val movie=Repository.getMovieDetail(movieId)
                MovieData.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getTrendingMovie(media_type:String,time_window:String){
        viewModelScope.launch {
            try {
                val movie=Repository.getAllMovieData(media_type,time_window)
                TrendingMovie.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getMovieVideo(movieId: String){
        viewModelScope.launch {
            try {
                val movie=Repository.getMovieVideo(movieId)
                movieVideo.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getUpcomingMovie(){
        viewModelScope.launch {
            try {
                val movie=Repository.getUpcomingMovie()
                UpcomingMovie.postValue(movie)
            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
    fun getTopRatedMovie(){
        viewModelScope.launch {
            try {
                val movie=Repository.getTopRated()
                topRatedMovie.postValue(movie)
            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
    fun getPopularSeries(){
        viewModelScope.launch {
            try {
                val movie=Repository.getPopularSeries()
                PopularMovie.postValue(movie)
            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }
    fun  getSearchMovie(query:String){
        viewModelScope.launch {
            try {
                val movie=Repository.getSearchMovie(query)
                SearchMovie.postValue(movie)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}