package com.example.simplenotes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenotes.R
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.callback.OnTouchItem
import java.util.*
import kotlin.collections.ArrayList

class DataAdapter : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private val array: ArrayList<NoteItem> = ArrayList()
    lateinit var callback: OnTouchItem

    fun attachData(newArray: ArrayList<NoteItem>) {
        array.clear()
        array += newArray
        notifyDataSetChanged()
    }

    fun insertItem(item: NoteItem) {
        array += item
        notifyItemInserted(array.lastIndex)
    }

    fun onItemMove(from: Int, to: Int) {
        Collections.swap(array, from, to)
        notifyItemMoved(from, to)
    }

    fun onItemDismiss(position: Int) {
        val item = array[position]
        array -= item
        notifyItemRemoved(position)
        callback.onItemDismiss(item)
    }

    fun editItem(oldItem: NoteItem, title: String, text: String) {
        val position = array.indexOf(oldItem)
        val item = array[position]
        item.title = title
        item.text = text
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(array[position])

    override fun getItemCount(): Int = array.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.itemTitle)
        private val text: TextView = itemView.findViewById(R.id.itemText)
        private val timeCreated: TextView = itemView.findViewById(R.id.timeCreated)

        fun bind(item: NoteItem) {
            title.text = item.title
            text.text = item.text
            timeCreated.text = item.timeCreated
            itemView.setOnClickListener {
                callback.onItemClicked(item)
            }
        }
    }
}