package com.example.simplenotes.presentation.presenter

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.simplenotes.domain.MainModel
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.presentation.adapter.DataAdapter
import com.example.simplenotes.presentation.adapter.ItemTouchHelperCallback
import com.example.simplenotes.presentation.adapter.callback.OnTouchItem
import com.example.simplenotes.presentation.ui.fragment.main.IMainFragmentView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainFragmentPresenter : MvpPresenter<IMainFragmentView>() {

    override fun onFirstViewAttach() {
        MainModel.adapter = adapter
        viewState.setAdapter(adapter)
        viewState.checkItemCountRV()
        super.onFirstViewAttach()
    }

    private val adapter = DataAdapter().apply {
        attachData(MainModel.data)
        callback = object : OnTouchItem {
            override fun onItemClicked(item: NoteItem) {
                viewState.navigateToDetail(item = item)
            }

            override fun onItemDismiss() {
                viewState.showUndoShackBar()
                viewState.checkItemCountRV()
            }
        }
    }

    internal val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
}