package com.example.simplenotes.domain.model

import android.content.ContentValues
import com.example.simplenotes.data.NoteReaderContract
import com.example.simplenotes.data.NoteReaderContract.NoteEntry.TABLE_NAME
import com.example.simplenotes.data.ReaderDbHelper
import com.example.simplenotes.domain.MyApp
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter


object MainModel {
    lateinit var adapter: DataAdapter
    private val dbHelper: ReaderDbHelper = ReaderDbHelper(MyApp.instance.baseContext)

    fun readData(): ArrayList<NoteItem> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        val result: ArrayList<NoteItem> = ArrayList()
        while (!cursor.isAfterLast) {
            val item = NoteItem(
                title = cursor.getString(1),
                text = cursor.getString(2),
                timeCreated = cursor.getString(3)
            )
            cursor.moveToNext();
            result.add(item);
        }
        cursor.close()
        db.close()
        return result
    }

    fun createData(item: NoteItem) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(NoteReaderContract.NoteEntry.COLUMN_NAME_TITLE, item.title)
            put(NoteReaderContract.NoteEntry.COLUMN_NAME_TEXT, item.text)
            put(NoteReaderContract.NoteEntry.COLUMN_NAME_DATE, item.timeCreated)
        }

        db.insert(NoteReaderContract.NoteEntry.TABLE_NAME, null, values)
    }


    fun updateData() {


    }

    fun deleteData() {

    }
}