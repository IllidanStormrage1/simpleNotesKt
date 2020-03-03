package com.example.simplenotes.domain.entity

import java.io.Serializable


data class NoteItem(
    val title: String,
    val text: String
) : Serializable