package com.example.simplenotes.domain.utils

import android.content.SharedPreferences

internal inline fun SharedPreferences.apply(action: SharedPreferences.Editor.() -> Unit) =
    with(this.edit()) {
        action(this)
        this.apply()
    }
