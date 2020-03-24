package com.example.simplenotes.domain.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager

/**
 * Created by kirill on 24.03.2020.
 */

inline fun SharedPreferences.apply(action: SharedPreferences.Editor.() -> Unit) =
    with(this.edit()) {
        action(this)
        this.apply()
    }

fun Activity.hideKeyboard() {
    val inputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusedView = currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun <T> MutableCollection<T>.clearAndAddAll(values: Collection<T>) {
    clear()
    addAll(elements = values)
}