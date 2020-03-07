package com.example.simplenotes.presentation.adapter.callback

import com.example.simplenotes.domain.entity.NoteItem

interface OnTouchItem {

    fun onItemClicked(item: NoteItem)
    fun onItemDismiss(item: NoteItem)
}