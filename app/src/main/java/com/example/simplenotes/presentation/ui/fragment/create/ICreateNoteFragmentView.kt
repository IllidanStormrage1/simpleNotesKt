package com.example.simplenotes.presentation.ui.fragment.create

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface ICreateNoteFragmentView : MvpView {
    @Skip
    fun navigateToMainFragment()
}