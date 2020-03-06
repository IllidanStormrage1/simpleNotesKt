package com.example.simplenotes.presentation.presenter

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.simplenotes.data.ReaderDbHelper
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.model.MainModel
import com.example.simplenotes.presentation.adapter.DataAdapter
import com.example.simplenotes.presentation.adapter.ItemTouchHelperCallback
import com.example.simplenotes.presentation.adapter.callback.OnTouchItem
import com.example.simplenotes.presentation.ui.fragment.main.IMainFragmentView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainFragmentPresenter : MvpPresenter<IMainFragmentView>() {
    lateinit var viewContext: Context

    override fun onFirstViewAttach() {
        viewState.setAdapter(adapter)
        MainModel.adapter = adapter
        MainModel.dbHelper = ReaderDbHelper(viewContext)

        CoroutineScope(Dispatchers.Main).launch {
            viewState.showProgressBar()
            MainModel.adapter.attachData(readDataFromDb())
            viewState.hideProgressBar()
            viewState.checkItemCountRV()
        }

        super.onFirstViewAttach()
    }

    private suspend fun readDataFromDb() =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            MainModel.readData()
        }

    private val adapter = DataAdapter().apply {
        callback = object : OnTouchItem {
            override fun onItemClicked(item: NoteItem) {
                viewState.navigateToDetail(item)
            }

            override fun onItemDismiss() {
                viewState.showUndoShackBar()
                viewState.checkItemCountRV()
            }
        }
    }

    internal val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
}
