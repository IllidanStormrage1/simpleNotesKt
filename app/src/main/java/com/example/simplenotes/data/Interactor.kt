package com.example.simplenotes.data

import android.content.ContentValues
import com.example.simplenotes.SimpleNotes
import com.example.simplenotes.data.base.NoteReaderContract
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_DATE
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_ID
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_POSITION
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_TEXT
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_TITLE
import com.example.simplenotes.data.base.ReaderDbHelper
import com.example.simplenotes.domain.entity.NoteItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Interactor {

    private val dbHelper: ReaderDbHelper =
        ReaderDbHelper(SimpleNotes.instance.applicationContext)

    fun createDataInBase(item: NoteItem, position: Int = 0) =
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NOTE_ID, item.id)
                put(COLUMN_NOTE_TITLE, item.title)
                put(COLUMN_NOTE_TEXT, item.text)
                put(COLUMN_NOTE_POSITION, position)
                put(COLUMN_NOTE_DATE, item.timeCreated)
            }
            db.insert(NoteReaderContract.NoteEntry.TABLE_NAME, null, values)
        }

    suspend fun readData(): MutableList<NoteItem> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val result: MutableList<NoteItem> = mutableListOf()
            val db = dbHelper.readableDatabase
            val cursor =
                db.query(
                    NoteReaderContract.NoteEntry.TABLE_NAME,
                    arrayOf(
                        COLUMN_NOTE_TITLE,
                        COLUMN_NOTE_TEXT,
                        COLUMN_NOTE_DATE,
                        COLUMN_NOTE_ID,
                        COLUMN_NOTE_POSITION
                    ),
                    null,
                    null,
                    null,
                    null,
                    COLUMN_NOTE_POSITION
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

    fun updateDataInBase(id: Long, title: String, text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            val newValues = ContentValues().apply {
                put(COLUMN_NOTE_TITLE, title)
                put(COLUMN_NOTE_TEXT, text)
            }
            db.update(
                NoteReaderContract.NoteEntry.TABLE_NAME,
                newValues,
                "$COLUMN_NOTE_ID = $id",
                null
            )
        }
    }

    fun deleteData(id: Long) =
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            db.delete(
                NoteReaderContract.NoteEntry.TABLE_NAME,
                "id = ?",
                arrayOf("$id")
            )
        }


    fun swap(fromPosition: Int, toPosition: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.readableDatabase

        }
    }
}