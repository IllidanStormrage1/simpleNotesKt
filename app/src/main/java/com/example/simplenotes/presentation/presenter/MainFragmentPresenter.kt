package com.example.simplenotes.presentation.presenter

import android.content.Context
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
    lateinit var contextView: Context

    override fun onFirstViewAttach() {
        MainModel.adapter = adapter
        with(viewState) {
            setAdapter(adapter)
            checkItemCountRV()
        }
        super.onFirstViewAttach()
    }

    private val adapter = DataAdapter().apply {
        var data: ArrayList<NoteItem>
        CoroutineScope(Dispatchers.IO).launch {
            data = MainModel.readData()
            attachData(data)
        }
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