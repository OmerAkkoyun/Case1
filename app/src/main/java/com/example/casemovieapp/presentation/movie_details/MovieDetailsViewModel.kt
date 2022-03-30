package com.example.casemovieapp.presentation.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casemovieapp.common.Resource
import com.example.casemovieapp.domain.model.MovieDetails
import com.example.casemovieapp.domain.use_case.TheMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val theMovieUseCase: TheMovieUseCase) :
    ViewModel() {

    val movieDetails = MutableLiveData<MovieDetails>()
    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<String>()

    fun getMovieDetailsById(movieId: String) {
        viewModelScope.launch {
            theMovieUseCase.getMovieDetailsById(movieId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        loadingState.value = true
                    }
                    is Resource.Success -> {
                        loadingState.value = false
                        movieDetails.value = result.data!!

                    }
                    is Resource.Error -> {
                        loadingState.value = false
                        errorState.value = result.message?.toString()
                    }
                }
            }
        }
    }

}