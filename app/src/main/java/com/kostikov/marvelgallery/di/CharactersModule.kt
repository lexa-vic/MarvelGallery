package com.kostikov.marvelgallery.di

import com.kostikov.marvelgallery.data.MarvelRepository
import com.kostikov.marvelgallery.data.MarvelRepositoryImpl
import com.kostikov.marvelgallery.ui.characters.CharactersViewModelFactory
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

    @PerActivity
    @Provides
    fun provideCharactersViewModelFactory(marvelRepository: MarvelRepository): CharactersViewModelFactory {
        return CharactersViewModelFactory(marvelRepository)
    }
}