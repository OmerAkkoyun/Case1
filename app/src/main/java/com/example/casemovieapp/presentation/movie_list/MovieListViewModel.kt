package com.example.casemovieapp.presentation.movie_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.casemovieapp.common.Resource
import com.example.casemovieapp.common.SingleLiveEvent
import com.example.casemovieapp.domain.model.Movie
import com.example.casemovieapp.domain.use_case.TheMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val theMovieUseCase: TheMovieUseCase) :
    ViewModel() {

    val nowPlayingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies = MutableLiveData<List<Movie>>()
    val loadingState = MutableLiveData<Boolean>()
    val errorState = SingleLiveEvent<String>()



    fun getNowPlayingMovies() {
        viewModelScope.launch {
            theMovieUseCase.getNowPlayingMovies().collect{ result ->
                when (result) {
                    is Resource.Loading -> {
                        loadingState.value = true
                    }
                    is Resource.Success -> {
                        loadingState.value = false
                        nowPlayingMovies.value = result.data!!

                    }
                    is Resource.Error -> {
                        loadingState.value = false
                        errorState.value = result.message?.toString()
                    }
                }
            }
        }
    }

    fun getUpComingMovies() {
        viewModelScope.launch {
            theMovieUseCase.getUpComingMovies().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        loadingState.value = true
                    }
                    is Resource.Success -> {
                        loadingState.value = false
                        upcomingMovies.value = result.data!!

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