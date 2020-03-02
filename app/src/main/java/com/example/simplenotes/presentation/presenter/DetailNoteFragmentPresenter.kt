package com.example.simplenotes.presentation.presenter

import com.example.simplenotes.domain.MainModel
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.ui.fragment.detail.IDetailNoteFragmentView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailNoteFragmentPresenter : MvpPresenter<IDetailNoteFragmentView>() {

    fun onBackPressed(item: NoteItem, title: String, text: String) {
        MainModel.adapter.editItem(item, NoteItem(title, text))
        viewState.navigateToMainFragment()
    }
}