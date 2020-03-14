package com.example.simplenotes

import android.app.Application

class SimpleNotes : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: SimpleNotes
            private set
    }
}