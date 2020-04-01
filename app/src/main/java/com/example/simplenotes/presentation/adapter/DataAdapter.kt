package com.example.simplenotes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenotes.R
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.utils.clearAndAddAll
import com.example.simplenotes.presentation.adapter.callback.OnTouchItem
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.*

class DataAdapter : RecyclerView.Adapter<DataAdapter.NoteViewHolder>() {

    private val list: MutableList<NoteItem> = mutableListOf()
    lateinit var callback: OnTouchItem

    fun attachData(newArray: Collection<NoteItem>) {
        list.clearAndAddAll(newArray)
        notifyDataSetChanged()
    }

    fun insertItem(item: NoteItem, position: Int) {
        list.add(position, item)
        notifyItemInserted(position)
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        notifyItemMoved(fromPosition, toPosition)
        callback.onItemSwap(fromPosition, toPosition)
    }

    fun onItemDismiss(position: Int) {
        val item = list[position]
        list.removeAt(position)
        notifyItemRemoved(position)
        callback.onItemDismiss(item, position)
    }

    fun editItem(oldItem: NoteItem, title: String, text: String) {
        val position = list.indexOf(oldItem)
        val item = list[position]
        item.title = title
        item.text = text
        notifyItemChanged(position)
    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        Collections.swap(list, fromPosition, toPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        with(LayoutInflater.from(parent.context)) {
            NoteViewHolder(inflate(R.layout.note_item, parent, false))
        }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = list[position].id

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            callback.onItemClicked(list[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: NoteItem) = with(itemView) {
            itemTitle.text = item.title
            itemText.text = item.text
            itemTimeCreated.text = item.timeCreated
        }
    }
}