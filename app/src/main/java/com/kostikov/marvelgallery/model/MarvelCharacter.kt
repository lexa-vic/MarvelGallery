package com.kostikov.marvelgallery.model

import com.kostikov.marvelgallery.data.dto.CharacterMarvelDto

/**
 * @author Kostikov Aleksey.
 */
class MarvelCharacter(val name: String,
                        val imageUrl: String) {

    constructor(dto: CharacterMarvelDto): this(dto.name, dto.imageUrl)
}
