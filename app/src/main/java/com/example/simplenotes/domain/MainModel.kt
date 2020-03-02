package com.example.simplenotes.domain

import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter


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