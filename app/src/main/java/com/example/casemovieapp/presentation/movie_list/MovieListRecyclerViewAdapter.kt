package com.example.casemovieapp.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.casemovieapp.R
import com.example.casemovieapp.databinding.ItemListViewBinding
import com.example.casemovieapp.domain.model.Movie
import com.example.casemovieapp.presentation.interfaces.IOnListItemClickListener

class MovieListRecyclerViewAdapter (
    private val movieList: List<Movie>,
    private val iOnListItemClickListener: IOnListItemClickListener
        ) : RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.binding.itemRootLayout.setOnClickListener {
            iOnListItemClickListener.onListItemClickListener(movieList[position].movie_id)
        }
    }

    override fun getItemCount() = movieList.size


    class ViewHolder (val binding: ItemListViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie : Movie){
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

}