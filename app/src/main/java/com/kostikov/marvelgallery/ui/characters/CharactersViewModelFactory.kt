package com.kostikov.marvelgallery.ui.characters

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kostikov.marvelgallery.data.MarvelRepository

/**
 * @author Kostikov Aleksey.
 */
class CharactersViewModelFactory(private val charactersRepository: MarvelRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(charactersRepository) as T
    }
}