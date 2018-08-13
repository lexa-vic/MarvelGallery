package com.kostikov.marvelgallery.data.dto

/**
 * @author Kostikov Aleksey.
 */

class DataContainer<T> {
    var results: T? = null
}

class DataWrapper<T> {
    var data: DataContainer<T>? = null
}

class ImageDto {
    lateinit var path: String
    lateinit var extension: String
    val completeImagePath: String
        get() = "$path.$extension"
}

class CharacterMarvelDto {
    lateinit var name: String
    lateinit var thumbnail: ImageDto
    val imageUrl: String
        get() = thumbnail.completeImagePath
}