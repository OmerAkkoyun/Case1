package com.example.casemovieapp.di

import com.example.casemovieapp.common.Constants
import com.example.casemovieapp.data.remote.ITheMovieApi
import com.example.casemovieapp.data.repository.TheMovieRepositoryImpl
import com.example.casemovieapp.domain.repository.IDomainTheMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideTheMovieApi(): ITheMovieApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ITheMovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTheMovieRepository(theMovieApi: ITheMovieApi) : IDomainTheMovieRepository{
        return TheMovieRepositoryImpl(theMovieApi)
    }

}