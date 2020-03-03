package com.example.simplenotes.presentation.presenter

import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.model.MainModel
import com.example.simplenotes.presentation.ui.fragment.create.ICreateNoteFragmentView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CreateNoteFragmentPresenter : MvpPresenter<ICreateNoteFragmentView>() {

    fun onFabPressed(title: String, text: String) {
        if (title.isNotBlank() || text.isNotBlank()) {
            val newItem = NoteItem(title = title, text = text)

            /* todo */
            MainModel.adapter.insertItem(newItem)
        }
        viewState.navigateToMainFragment()
    }
}