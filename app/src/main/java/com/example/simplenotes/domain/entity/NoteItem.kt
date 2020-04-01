package com.example.simplenotes.domain.entity

import java.io.Serializable


class NoteItem(
    var title: String?,
    var text: String?,
    val timeCreated: String,
    val id: Long
) : Serializable