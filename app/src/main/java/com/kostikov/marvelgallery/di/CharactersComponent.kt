package com.kostikov.marvelgallery.di

import com.kostikov.marvelgallery.ui.characters.MainActivity
import dagger.Subcomponent

/**
 * @author Kostikov Aleksey.
 */
@PerActivity
@Subcomponent(
    modules = arrayOf(
        CharactersModule::class
    )
)
interface CharactersComponent {

    fun inject(activity: MainActivity)
}