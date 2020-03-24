package com.example.simplenotes.domain.utils

import java.util.*

object Constants {
    const val ITEM = "item"
    const val SP_THEME = "theme"
    const val SP_DARK_THEME = "darkTheme"
    const val SP_DARK = "dark"
    const val PATTERN_DATE = "hh:mm dd.MM.yy"
}

fun generateUUID() = with(UUID.randomUUID()) {
    toString().replace("-", "").hashCode().toLong()
}
