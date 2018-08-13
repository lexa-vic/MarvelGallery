package com.kostikov.marvelgallery.di

import com.kostikov.marvelgallery.data.network.MarvelApi
import com.kostikov.marvelgallery.data.network.retrofit
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Kostikov Aleksey.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMarvelApi(): MarvelApi{
        return retrofit.create(MarvelApi::class.java)
    }
}