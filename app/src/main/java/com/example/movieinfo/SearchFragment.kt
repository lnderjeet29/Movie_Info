package com.example.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieinfo.ViewModel.MovieViewModel
import com.example.movieinfo.adapter.MovieAdapter
import com.example.movieinfo.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private val binding:FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }
    private val homeAdapter by lazy {
        MovieAdapter{movie->
            val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailShow(movie)
            findNavController().navigate(action)
        }
    }
    private val homeViewModel:MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        hideToolbar()
        binding.rcSearch.adapter=homeAdapter
        observe()
       binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(query: String?): Boolean {

               return true
           }

           override fun onQueryTextChange(newText: String?): Boolean {
               homeViewModel.getSearchMovie(newText.toString())
               return true
           }

       })


        return binding.root
    }

    private fun observe() {
        homeViewModel.SearchMovie.observe(viewLifecycleOwner){
            homeAdapter.submitList(it.results)
        }
    }
    private fun hideToolbar(){
        activity?.findViewById<Toolbar>(R.id.cumstom_toolbar)?.isVisible=false
    }

    override fun onDestroy() {
        activity?.findViewById<Toolbar>(R.id.cumstom_toolbar)?.isVisible=true
        super.onDestroy()
    }
}

