package com.kostikov.marvelgallery.ui.characters

import android.arch.lifecycle.ViewModel
import com.kostikov.marvelgallery.data.MarvelRepository
import com.kostikov.marvelgallery.model.MarvelCharacter

/**
 * @author Kostikov Aleksey.
 */
class CharactersViewModel(val charactersRepository: MarvelRepository): ViewModel() {



    val characters = mutableListOf(
            MarvelCharacter(name = "3-D Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"),
            MarvelCharacter(name = "Abomination (Emil Blonsky)", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg")
    )

    fun getCharactersList(){

    }

}