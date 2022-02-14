package com.example.movieinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieinfo.R
import com.example.movieinfo.Repo.Network
import com.example.movieinfo.databinding.FragmentRecyclerViewItemBinding
import com.example.movieinfo.model.Movie

class MovieAdapter(val MPosition:(String)->Unit):ListAdapter<Movie,MovieAdapter.viewholder>(HomeDiff) {

    object HomeDiff:DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }

    }

    inner class viewholder(val binding:FragmentRecyclerViewItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(FragmentRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val movie=getItem(position)
        holder.binding.apply {
            movieImg.load(Network.imageUrl+movie.poster_path){
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
            }
            movieImg.setOnClickListener {
                MPosition.invoke(movie.id.toString())
            }

        }
    }

}