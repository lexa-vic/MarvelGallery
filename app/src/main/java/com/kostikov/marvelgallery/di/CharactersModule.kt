package com.kostikov.marvelgallery.di

import com.kostikov.marvelgallery.data.MarvelRepository
import com.kostikov.marvelgallery.data.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * @author Kostikov Aleksey.
 */

@Module
class CharactersModule {

    @PerActivity
    @Provides
    fun provideMarvelRepository(): MarvelRepository {
        return MarvelRepositoryImpl()
    }
}