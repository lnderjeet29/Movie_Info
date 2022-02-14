package com.example.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movieinfo.ViewModel.MovieViewModel
import com.example.movieinfo.adapter.MovieAdapter
import com.example.movieinfo.databinding.FragmentMovieDetailShowBinding
import com.example.movieinfo.model.Movie
import com.example.movieinfo.model.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class MovieDetailShow : Fragment() {

    private val args:MovieDetailShowArgs by navArgs()
    private val binding:FragmentMovieDetailShowBinding by lazy {
        FragmentMovieDetailShowBinding.inflate(layoutInflater)
    }
    private val homeViewModel:MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }
    private val homeAdapter by lazy {
        MovieAdapter{movie->
            val action=MovieDetailShowDirections.actionMovieDetailShowSelf(movie)
            findNavController().navigate(action)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observe()
        homeViewModel.getMovieDetails(args.movieId)
        homeViewModel.getTrendingMovie(Trending.Media_type_all,Trending.Time_Zone_Week)
        binding.MdRc.adapter=homeAdapter
        return binding.root
    }

    private fun observe() {
        homeViewModel.movieVideo.observe(viewLifecycleOwner){
            showVideo(it)
        }
        homeViewModel.MovieData.observe(viewLifecycleOwner){
            homeViewModel.getMovieVideo(it.id.toString())
            setData(it)
        }
        homeViewModel.TrendingMovie.observe(viewLifecycleOwner){
            homeAdapter.submitList(it.results)
        }
    }

    private fun setData(it: Movie?) {
        binding.apply {
            MdMovieImg.load("https://image.tmdb.org/t/p/w500"+it?.poster_path){
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            MdMoviePost1.load("https://image.tmdb.org/t/p/w500"+it?.poster_path){
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            MdMoviePost2.load("https://image.tmdb.org/t/p/w500"+it?.backdrop_path){
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            movieName.text=it?.original_title
            movieDis.text=it?.overview
        }
    }

    private fun showVideo(it: Video?) {

        val mediaController=MediaController(requireContext())
        mediaController.setAnchorView(binding.videoClip)
        it?.results?.let { movies->
            if(movies.isNotEmpty()){

                val youTubePlayerView: YouTubePlayerView = binding.videoClip
                lifecycle.addObserver(youTubePlayerView)

                youTubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {

                            val videoId = movies[0].key
                            youTubePlayer.loadVideo(videoId, 0f)
                        youTubePlayer.pause()


                    }

                })
                binding.videoClip.isVisible=true
            }else{
                binding.videoClip.isVisible=false
            }


        }

    }


}