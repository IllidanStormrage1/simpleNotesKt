package com.example.simplenotes.domain

import com.example.simplenotes.domain.adapter.DataAdapter
import com.example.simplenotes.domain.entity.NoteItem


object MainModel {
    lateinit var adapter: DataAdapter

    val data: ArrayList<NoteItem> = arrayListOf(
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу"),
        NoteItem(title = "Сделать домашку в шкилу", text = "Момтемотичка сука бляуууууууу")
    )
}