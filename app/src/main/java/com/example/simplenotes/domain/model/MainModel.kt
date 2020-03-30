package com.example.simplenotes.domain.model

import com.example.simplenotes.data.Interactor
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter


object MainModel {

    lateinit var adapter: DataAdapter
    private val interactor = Interactor()
    var bufferedItem: Pair<Int, NoteItem>? = null
        private set

    fun createData(
        item: NoteItem,
        position: Int = adapter.itemCount
    ) {
        adapter.insertItem(position = position, item = item)
        interactor.createDataInBase(item = item, position = position)
    }

    suspend fun readData() = interactor.readData()

    fun updateData(item: NoteItem, title: String, text: String) {
        adapter.editItem(item, title, text)
        interactor.updateDataInBase(item.id, title, text)
    }

    fun deleteData(item: NoteItem, position: Int = 0) {
        interactor.deleteData(item.id)
        bufferedItem = position to item
    }

    fun swap(fromPosition: Int, toPosition: Int) {
        interactor.swapItems(
            fromPosition, toPosition, idFrom = adapter.getItemId(fromPosition), idTo = adapter
                .getItemId(toPosition)
        )
        adapter.swapItems(fromPosition, toPosition)
    }
}