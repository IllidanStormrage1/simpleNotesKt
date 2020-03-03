package com.example.simplenotes.domain.model

import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter


object MainModel {
    lateinit var adapter: DataAdapter
    val data: ArrayList<NoteItem>
        get() = arrayListOf(
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg"),
            NoteItem("fsdf", "dfgdfg")
        )
}