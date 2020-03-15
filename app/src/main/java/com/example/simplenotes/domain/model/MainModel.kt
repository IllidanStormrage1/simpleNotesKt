package com.example.simplenotes.domain.model

import com.example.simplenotes.data.Interactor
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter


object MainModel {

    lateinit var adapter: DataAdapter // todo удалить это нахер
    private val interactor = Interactor()
    var bufferedItem: NoteItem? = null
    
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
        bufferedItem = item
    }
}