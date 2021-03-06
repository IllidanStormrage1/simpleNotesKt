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
            val timeCreated = SimpleDateFormat(
                PATTERN_DATE,
                Locale.getDefault()
            ).format(Date())
            val id = generateUUID()
            val item = NoteItem(title, text, timeCreated, id)
            MainModel.createData(item = item)
        }
        viewState.navigateToMainFragment()
    }

}