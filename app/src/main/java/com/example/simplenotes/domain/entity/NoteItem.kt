package com.example.simplenotes.domain.entity

import java.io.Serializable


class NoteItem(
    val title: String,
    val text: String,
    val timeCreated: String? = null,
    val id: Long
) : Serializable