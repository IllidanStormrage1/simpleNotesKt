package com.example.simplenotes.domain.utils

import android.content.SharedPreferences
import java.util.*

internal inline fun SharedPreferences.apply(action: SharedPreferences.Editor.() -> Unit) =
    with(this.edit()) {
        action(this)
        this.apply()
    }

internal fun generateUUID() = with(UUID.randomUUID()) {
    toString().replace("-", "").hashCode().toLong()
}