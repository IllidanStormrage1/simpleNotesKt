package com.example.simplenotes.data

import android.content.ContentValues
import com.example.simplenotes.SimpleNotes
import com.example.simplenotes.domain.entity.NoteItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class Interactor {

    private val dbHelper: ReaderDbHelper = ReaderDbHelper(SimpleNotes.instance.applicationContext)

    fun createDataInBase(item: NoteItem) =
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put(NoteReaderContract.NoteEntry.COLUMN_NOTE_ID, item.id)
                put(NoteReaderContract.NoteEntry.COLUMN_NOTE_TITLE, item.title)
                put(NoteReaderContract.NoteEntry.COLUMN_NOTE_TEXT, item.text)
                put(NoteReaderContract.NoteEntry.COLUMN_NOTE_DATE, item.timeCreated)
            }
            db.insert(NoteReaderContract.NoteEntry.TABLE_NAME, null, values)
        }

    suspend fun readData(): ArrayList<NoteItem> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val result: ArrayList<NoteItem> = ArrayList()
            val db = dbHelper.readableDatabase
            val cursor =
                db.query(
                    NoteReaderContract.NoteEntry.TABLE_NAME,
                    arrayOf(
                        NoteReaderContract.NoteEntry.COLUMN_NOTE_TITLE,
                        NoteReaderContract.NoteEntry.COLUMN_NOTE_TEXT,
                        NoteReaderContract.NoteEntry.COLUMN_NOTE_DATE,
                        NoteReaderContract.NoteEntry.COLUMN_NOTE_ID
                    ),
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

    fun updateDataInBase(id: Long, title: String, text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = dbHelper.writableDatabase
            val newValues = ContentValues().apply {
                put(NoteReaderContract.NoteEntry.COLUMN_NOTE_TITLE, title)
                put(NoteReaderContract.NoteEntry.COLUMN_NOTE_TEXT, text)
            }
            db.update(
                NoteReaderContract.NoteEntry.TABLE_NAME,
                newValues,
                "${NoteReaderContract.NoteEntry.COLUMN_NOTE_ID} = $id",
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
}