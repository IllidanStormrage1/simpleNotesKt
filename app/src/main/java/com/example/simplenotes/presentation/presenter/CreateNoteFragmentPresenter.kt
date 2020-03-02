package com.example.simplenotes.presentation.presenter

import com.example.simplenotes.domain.MainModel
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.ui.fragment.create.ICreateNoteFragmentView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CreateNoteFragmentPresenter : MvpPresenter<ICreateNoteFragmentView>() {

    fun onBackPressed(item: Pair<String, String>) {
        if (item.first.isNotBlank() || item.second.isNotBlank()) {
            MainModel.adapter.insertItem(NoteItem(item.first, item.second))
        }
        viewState.navigateToMainFragment()
    }
}