package com.kostikov.marvelgallery.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

typealias AnyItemAdapter = ItemAdapter<out RecyclerView.ViewHolder>

/**
 * @author Kostikov Aleksey.
 */
open class RecyclerListAdapter(var items: List<AnyItemAdapter> = listOf()): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return items.first{ it.getType() == viewType}.createViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].getType()

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindViewHolder(viewHolder)
    }
}

