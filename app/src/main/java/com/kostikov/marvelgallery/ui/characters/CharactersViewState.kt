package com.kostikov.marvelgallery.ui.characters

import com.kostikov.marvelgallery.model.MarvelCharacter

/**
 * @author Kostikov Aleksey.
 */

sealed class CharactersViewState

data class CharacterLoading(val state: Boolean): CharactersViewState()

data class CharacterError(val throwable: Throwable): CharactersViewState()

data class CharacterSuccess(val list: List<MarvelCharacter>): CharactersViewState()