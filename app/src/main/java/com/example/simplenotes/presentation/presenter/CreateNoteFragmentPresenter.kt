package com.example.simplenotes.presentation.presenter

import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.model.MainModel
import com.example.simplenotes.domain.utils.Constants.PATTERN_DATE
import com.example.simplenotes.domain.utils.generateUUID
import com.example.simplenotes.presentation.ui.fragment.create.ICreateNoteFragmentView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class CreateNoteFragmentPresenter : MvpPresenter<ICreateNoteFragmentView>() {

    fun onFabPressed(title: String, text: String) {
        if (title.isNotBlank() or text.isNotBlank()) {
            val newItem = NoteItem(
                title = title,
                text = text,
                timeCreated = SimpleDateFormat(
                    PATTERN_DATE,
                    Locale.getDefault()
                ).format(Date()),
                id = generateUUID()
            )
            MainModel.adapter.insertItem(newItem)
            MainModel.createData(newItem)
        }
        viewState.navigateToMainFragment()
    }
}