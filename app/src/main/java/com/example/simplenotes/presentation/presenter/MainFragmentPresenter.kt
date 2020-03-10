package com.example.simplenotes.presentation.presenter

import android.content.Context
import android.view.View
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

            override fun onItemDismiss(item: NoteItem) {
                viewState.showUndoShackBar()
                viewState.checkItemCountRV()
                MainModel.deleteData(item.id)
                MainModel.addInStack(item)
            }
        }
    }

    val undoCallback = View.OnClickListener {
        val item = MainModel.returnItemFromStack()
        MainModel.createData(item)
        MainModel.adapter.insertItem(item)
        viewState.checkItemCountRV()
    }

    val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
}
