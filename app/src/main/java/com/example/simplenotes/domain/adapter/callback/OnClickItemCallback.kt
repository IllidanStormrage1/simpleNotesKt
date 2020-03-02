package com.example.simplenotes.domain.adapter.callback

import com.example.simplenotes.domain.entity.NoteItem

interface OnClickItemCallback {
    fun onItemClicked(item: NoteItem)
}