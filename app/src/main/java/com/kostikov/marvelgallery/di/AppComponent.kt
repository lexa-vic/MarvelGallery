package com.kostikov.marvelgallery.di

import com.kostikov.marvelgallery.data.MarvelRepositoryImpl
import dagger.Component
import javax.inject.Singleton

/**
 * @author Kostikov Aleksey.
 */
@Singleton
@Component(
    modules = arrayOf(AppModule::class)
)
interface AppComponent {

    fun inject(repository: MarvelRepositoryImpl)

    fun provideCharacterComponent(): CharactersComponent
}