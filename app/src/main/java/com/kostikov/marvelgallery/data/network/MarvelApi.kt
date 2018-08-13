package com.kostikov.marvelgallery.data.network

import com.kostikov.marvelgallery.data.dto.CharacterMarvelDto
import com.kostikov.marvelgallery.data.dto.DataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Kostikov Aleksey.
 */
interface MarvelApi {

    @GET("characters")
    fun getCharacters(
            @Query("offset") offset: Int?,
            @Query("limit") limit: Int?
    ): Single<DataWrapper<List<CharacterMarvelDto>>>
}