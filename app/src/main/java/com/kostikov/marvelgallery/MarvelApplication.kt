package com.kostikov.marvelgallery

import android.app.Application
import com.kostikov.marvelgallery.di.AppComponent
import com.kostikov.marvelgallery.di.CharactersComponent
import com.kostikov.marvelgallery.di.DaggerAppComponent

/**
 * @author Kostikov Aleksey.
 */
class MarvelApplication: Application() {

    companion object {
        private var charactersComponent: CharactersComponent? = null

        lateinit var appComponent: AppComponent

        fun getCharactersComponent(): CharactersComponent =
                charactersComponent ?: appComponent.provideCharacterComponent().also { charactersComponent = it }

        fun releaseCharactersComponent() {charactersComponent = null}

    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()

    }
}