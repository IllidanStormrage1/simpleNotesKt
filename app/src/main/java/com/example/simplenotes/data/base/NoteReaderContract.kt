package com.example.simplenotes.data.base

import android.provider.BaseColumns

object NoteReaderContract {
    object NoteEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NOTE_ID = "id"
        const val COLUMN_NOTE_TITLE = "title"
        const val COLUMN_NOTE_POSITION = "position"
        const val COLUMN_NOTE_TEXT = "text"
        const val COLUMN_NOTE_DATE = "date"
    }
}