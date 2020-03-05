package com.example.simplenotes.presentation.presenter

import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.model.MainModel
import com.example.simplenotes.presentation.ui.fragment.detail.IDetailNoteFragmentView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailNoteFragmentPresenter : MvpPresenter<IDetailNoteFragmentView>() {

    fun onFabPressed(item: NoteItem, title: String, text: String) {

        val newItem = NoteItem(title = title, text = text)
        MainModel.adapter.editItem(item, newItem)
        viewState.navigateToMainFragment()
    }
}