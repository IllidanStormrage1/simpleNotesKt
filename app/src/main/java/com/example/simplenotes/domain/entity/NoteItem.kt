package com.example.simplenotes.domain.entity

import java.io.Serializable


data class NoteItem(
    val title: String,
    val text: String,
    val timeCreated: String? = null,
    val id: Int = title.hashCode() + text.hashCode() * 228
) : Serializable