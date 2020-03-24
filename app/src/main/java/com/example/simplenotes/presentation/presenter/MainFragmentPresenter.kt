package com.example.simplenotes.presentation.presenter

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.model.MainModel
import com.example.simplenotes.presentation.adapter.DataAdapter
import com.example.simplenotes.presentation.adapter.ItemTouchHelperCallback
import com.example.simplenotes.presentation.adapter.callback.OnTouchItem
import com.example.simplenotes.presentation.ui.fragment.main.IMainFragmentView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainFragmentPresenter : MvpPresenter<IMainFragmentView>() {

    override fun onFirstViewAttach() {
        viewState.setAdapter(adapter)
        MainModel.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            viewState.showProgressBar()
            MainModel.adapter.attachData(MainModel.readData())
            viewState.checkItemCountRV()
            viewState.hideProgressBar()
        }
        super.onFirstViewAttach()
    }

    private val adapter = DataAdapter().apply {
        callback = object : OnTouchItem {
            override fun onItemClicked(item: NoteItem) {
                viewState.navigateToDetail(item)
            }

            override fun onItemDismiss(item: NoteItem, position: Int) {
                viewState.checkItemCountRV()
                viewState.showUndoShackBar()
                MainModel.deleteData(item, position)
            }

            override fun onItemSwap(fromPosition: Int, toPosition: Int) {
                MainModel.swap(fromPosition, toPosition)
            }
        }
    }

    val undoCallback = View.OnClickListener {
        MainModel.bufferedItem?.let {
            MainModel.createData(item = it.second, position = it.first)
        }
        viewState.checkItemCountRV()
    }

    val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
}
