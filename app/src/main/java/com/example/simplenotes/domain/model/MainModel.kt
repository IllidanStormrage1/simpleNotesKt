package com.example.simplenotes.domain.model

import android.content.ContentValues
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.COLUMN_NOTE_DATE
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.COLUMN_NOTE_ID
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.COLUMN_NOTE_TEXT
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.COLUMN_NOTE_TITLE
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.TABLE_NAME
import com.example.simplenotes.data.ReaderDbHelper
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object MainModel {
    lateinit var adapter: DataAdapter
    lateinit var dbHelper: ReaderDbHelper

    suspend fun readData(): ArrayList<NoteItem> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val result: ArrayList<NoteItem> = ArrayList()
            val db = dbHelper.readableDatabase
            val cursor =
                db.query(
                    TABLE_NAME,
                    arrayOf(COLUMN_NOTE_TITLE, COLUMN_NOTE_TEXT, COLUMN_NOTE_DATE, COLUMN_NOTE_ID),
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
                    timeCreated = cursor.getString(2),
                    id = cursor.getLong(3)
                )
                cursor.moveToNext()
                result += item
            }
            cursor.close()
            result
        }


    fun createData(item: NoteItem) =
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NOTE_ID, item.id)
                put(COLUMN_NOTE_TITLE, item.title)
                put(COLUMN_NOTE_TEXT, item.text)
                put(COLUMN_NOTE_DATE, item.timeCreated)
            }
            db.insert(TABLE_NAME, null, values)
        }


    fun updateData(id: Long, title: String, text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            val newValues = ContentValues().apply {
                put(COLUMN_NOTE_TITLE, title)
                put(COLUMN_NOTE_TEXT, text)
            }
            db.update(TABLE_NAME, newValues, "$COLUMN_NOTE_ID = $id", null)
        }

    }

    fun deleteData(id: Long) =
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            db.delete(
                TABLE_NAME,
                "id = ?",
                arrayOf("$id")
            )
        }

}