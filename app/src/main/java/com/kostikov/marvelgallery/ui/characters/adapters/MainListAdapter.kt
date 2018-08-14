package com.kostikov.marvelgallery.ui.characters.adapters

/**
 * @author Kostikov Aleksey.
 */
class MainListAdapter(items: List<AnyItemAdapter>): RecyclerListAdapter(items) {

    fun addItem(itemAdapter: AnyItemAdapter) {
        items += itemAdapter

        val index = items.indexOf(itemAdapter)

        if (index == -1) return
        notifyItemInserted(index)
    }

    fun deleteItem(itemAdapter: AnyItemAdapter) {
        val index = items.indexOf(itemAdapter)

        if (index == -1) return

        items -= itemAdapter
        notifyItemRemoved(index)
    }

    fun clear(){
        items = emptyList()
        notifyDataSetChanged()
    }
}