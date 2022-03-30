package com.example.casemovieapp.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.casemovieapp.R
import com.example.casemovieapp.databinding.ItemSliderViewBinding
import com.example.casemovieapp.domain.model.Movie
import com.example.casemovieapp.presentation.interfaces.IOnSliderItemClickListener
import com.smarteist.autoimageslider.SliderViewAdapter

class MovieListSliderAdapter(private var sliderList: List<Movie>, private var listener: IOnSliderItemClickListener) : SliderViewAdapter<MovieListSliderAdapter.SliderAdapterVH> () {

    override fun getCount() = sliderList.size

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        return SliderAdapterVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.item_slider_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder?.bind(sliderList[position])
        viewHolder?.binding?.sliderRootLayout?.setOnClickListener {
            listener.onSliderItemClickListener(sliderList[position].movie_id)
        }
    }


    class SliderAdapterVH(val binding: ItemSliderViewBinding) : SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(movieModel: Movie) {
            binding.movie = movieModel
            binding.executePendingBindings()

        }
    }
}