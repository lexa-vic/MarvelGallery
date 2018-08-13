package com.kostikov.marvelgallery.data

import com.kostikov.marvelgallery.model.MarvelCharacter
import io.reactivex.Single

/**
 * @author Kostikov Aleksey.
 */
interface MarvelRepository {

    fun getAllCharacters(): Single<List<MarvelCharacter>>
}