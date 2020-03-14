package com.example.simplenotes.domain.model

import com.example.simplenotes.data.Interactor
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter
import java.util.*


object MainModel {

    lateinit var adapter: DataAdapter // todo удалить это нахер
    private val interactor = Interactor()
    private val bufferedItems: Stack<NoteItem> = Stack()

    fun createData(item: NoteItem) {
        adapter.insertItem(item)
        interactor.createDataInBase(item)
    }

    suspend fun readData() = interactor.readData()

    fun updateData(item: NoteItem, title: String, text: String) {
        adapter.editItem(item, title, text)
        interactor.updateDataInBase(item.id, title, text)
    }

    fun deleteData(item: NoteItem) {
        interactor.deleteData(item.id)
        addInStack(item)
    }

    private fun addInStack(item: NoteItem) = bufferedItems.add(item)

    fun returnItemFromStack(): NoteItem = bufferedItems.pop()
}