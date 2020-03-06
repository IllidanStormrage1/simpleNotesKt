package com.example.simplenotes.domain.model

import android.content.ContentValues
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.COLUMN_NAME_DATE
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.COLUMN_NAME_TEXT
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.COLUMN_NAME_TITLE
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.TABLE_NAME
import com.example.simplenotes.data.ReaderDbHelper
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter


object MainModel {
    lateinit var adapter: DataAdapter
    lateinit var dbHelper: ReaderDbHelper

    fun readData(): ArrayList<NoteItem> {
        val result: ArrayList<NoteItem> = ArrayList()
        val db = dbHelper.readableDatabase
        val cursor =
            db.query(
                TABLE_NAME,
                arrayOf(COLUMN_NAME_TITLE, COLUMN_NAME_TEXT, COLUMN_NAME_DATE),
                null,
                null,
                null,
                null,
                null
            )
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val item = NoteItem(
                title = cursor.getString(0),
                text = cursor.getString(1),
                timeCreated = cursor.getString(2)
            )
            cursor.moveToNext()
            result += item
        }
        cursor.close()
        return result
    }

    fun createData(item: NoteItem) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME_TITLE, item.title)
            put(COLUMN_NAME_TEXT, item.text)
            put(COLUMN_NAME_DATE, item.timeCreated)
        }

        db.insert(TABLE_NAME, null, values)
    }


    fun updateData() {


    }

    fun deleteData() {

    }
}