package com.example.simplenotes.data

import android.provider.BaseColumns

object NoteReaderContract {
    // Table contents are grouped together in an anonymous object.
    object NoteEntry : BaseColumns {
        const val TABLE_NAME = "entry"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_TEXT = "text"
        const val COLUMN_NAME_DATE = "date"
    }
}