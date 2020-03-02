package com.example.simplenotes.presentation.presenter

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.simplenotes.domain.MainModel
import com.example.simplenotes.domain.adapter.DataAdapter
import com.example.simplenotes.domain.adapter.callback.OnTouchCallback
import com.example.simplenotes.domain.adapter.ItemTouchHelperCallback
import com.example.simplenotes.domain.adapter.callback.OnClickItemCallback
import com.example.simplenotes.domain.entity.NoteItem
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
        callback = object :
            OnClickItemCallback {
            override fun onItemClicked(item: NoteItem) {
                viewState.navigateToDetail(item)
            }
        }
        touchCallback = object :
            OnTouchCallback {
            override fun onItemDismiss() {
                viewState.checkItemCountRV()
                viewState.showUndoShackBar()
            }
        }
    }

    internal val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
}