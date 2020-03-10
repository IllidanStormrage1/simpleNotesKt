package com.example.simplenotes.presentation.ui.fragment.detail

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface IDetailNoteFragmentView : MvpView {
    @Skip
    fun navigateToMainFragment()
}