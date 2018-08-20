package com.kostikov.marvelgallery.extensions

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author Kostikov Aleksey.
 */

fun <T: View> RecyclerView.ViewHolder.bindView(viewId: Int) = lazy { itemView.findViewById<T>(viewId) }

fun ImageView.loadImage(url: String){
    Glide.with(context)
            .load(url)
            .into(this)
}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }