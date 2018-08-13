package com.kostikov.marvelgallery.adapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author Kostikov Aleksey.
 */
abstract class ItemAdapter<T: RecyclerView.ViewHolder> (@LayoutRes open val layoutId: Int){

    abstract fun createViewHolder(itemView: View): T

    abstract fun T.onBindViewHolder()

    open fun getType() = layoutId

    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as T).onBindViewHolder()
    }


}
