package com.kostikov.marvelgallery.data

import com.kostikov.marvelgallery.MarvelApplication
import com.kostikov.marvelgallery.data.network.MarvelApi
import com.kostikov.marvelgallery.model.MarvelCharacter
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Kostikov Aleksey.
 */
class MarvelRepositoryImpl: MarvelRepository {

    @Inject
    lateinit var api: MarvelApi

    init {
        MarvelApplication.appComponent.inject(this)
    }

    override fun getAllCharacters(): Single<List<MarvelCharacter>> = api.getCharacters(
            0,
            elementsOnListLimit
    ).map {
        it.data?.results.orEmpty().map(::MarvelCharacter)
    }

    companion object {
        const val elementsOnListLimit = 50
    }
}