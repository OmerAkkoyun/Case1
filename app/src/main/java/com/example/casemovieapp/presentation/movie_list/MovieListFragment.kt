package com.example.casemovieapp.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.casemovieapp.R
import com.example.casemovieapp.common.AlertDialogHelper
import com.example.casemovieapp.common.Constants
import com.example.casemovieapp.databinding.FragmentMovieListBinding
import com.example.casemovieapp.domain.model.Movie
import com.example.casemovieapp.presentation.interfaces.IOnListItemClickListener
import com.example.casemovieapp.presentation.interfaces.IOnSliderItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() , IOnSliderItemClickListener , IOnListItemClickListener{

    private val viewModel: MovieListViewModel by viewModels()
    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMovieListBinding.inflate(inflater,container,false)


        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNowPlayingMovies()


        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            binding.imageSlider.setSliderAdapter(MovieListSliderAdapter(it,this))
            viewModel.getUpComingMovies()
            binding.swipe.isRefreshing = false
        }
        viewModel.loadingState.observe(viewLifecycleOwner){ isLoading ->
            binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.swipe.isRefreshing = false
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner){
            binding.rvUpComingList.adapter = MovieListRecyclerViewAdapter(it,this)
            binding.swipe.isRefreshing = false
        }
        viewModel.errorState.observe(viewLifecycleOwner){
            binding.swipe.isRefreshing = false
            AlertDialogHelper.createSimpleAlertDialog(requireContext(),"",it,resources.getString(R.string.positive_button_ok))
        }

        binding.swipe.setOnRefreshListener {
            viewModel.getNowPlayingMovies()
        }
    }

    override fun onSliderItemClickListener(clickedMovieId:String) {
        openDetailFragment(clickedMovieId)
    }

    override fun onListItemClickListener(clickedMovieId:String) {
       openDetailFragment(clickedMovieId)
    }

    private fun openDetailFragment(clickedMovieId:String){
        val bundle = Bundle()
        bundle.putString(Constants.BUNDLE_DETAIL_ID_KEY, clickedMovieId)
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle)
    }

}