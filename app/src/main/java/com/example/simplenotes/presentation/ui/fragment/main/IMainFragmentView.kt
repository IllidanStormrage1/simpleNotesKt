package com.example.simplenotes.presentation.ui.fragment.main

import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface IMainFragmentView : MvpView {

    @AddToEndSingle
    fun checkItemCountRV()

    @AddToEndSingle
    fun setAdapter(adapter: DataAdapter)

    @Skip
    fun navigateToDetail(item: NoteItem)

    @Skip
    fun showUndoShackBar()

    @Skip
    fun hideProgressBar()

    @Skip
    fun showProgressBar()
}