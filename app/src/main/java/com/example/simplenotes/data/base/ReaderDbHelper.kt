package com.example.simplenotes.data.base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_DATE
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_ID
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_POSITION
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_TEXT
import com.example.simplenotes.data.base.NoteReaderContract.NoteEntry.COLUMN_NOTE_TITLE

class ReaderDbHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ) {
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "NoteReader.db"
        private val SQL_CREATE_ENTRIES = """
            CREATE TABLE ${NoteReaderContract.NoteEntry.TABLE_NAME}
                ($COLUMN_NOTE_ID INTEGER ,
                $COLUMN_NOTE_TITLE TEXT,
                $COLUMN_NOTE_TEXT TEXT,
                $COLUMN_NOTE_POSITION INTEGER,
                $COLUMN_NOTE_DATE TEXT)
                """.trim()

        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${NoteReaderContract.NoteEntry.TABLE_NAME}"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(p0)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}