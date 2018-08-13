package com.kostikov.marvelgallery.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kostikov.marvelgallery.R
import com.kostikov.marvelgallery.extensions.bindView
import com.kostikov.marvelgallery.extensions.loadImage
import com.kostikov.marvelgallery.model.MarvelCharacter

/**
 * @author Kostikov Aleksey.
 */
class CharacterItemAdapter(val character: MarvelCharacter): ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {

    override fun createViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        textView.text = character.name
        imageView.loadImage(character.imageUrl)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView by bindView<TextView>(R.id.textView)
        val imageView by bindView<ImageView>(R.id.imageView)
    }
}