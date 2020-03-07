package com.example.simplenotes.domain.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager
import java.util.*

internal inline fun SharedPreferences.apply(action: SharedPreferences.Editor.() -> Unit) =
    with(this.edit()) {
        action(this)
        this.apply()
    }

internal fun generateUUID() = with(UUID.randomUUID()) {
    toString().replace("-", "").hashCode().toLong()
}

fun hideKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusedView = activity.currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}