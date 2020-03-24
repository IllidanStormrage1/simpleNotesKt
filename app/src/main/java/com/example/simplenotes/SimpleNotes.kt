package com.example.simplenotes

import android.app.Application

class SimpleNotes : Application() {

    companion object {
        lateinit var instance: SimpleNotes
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}