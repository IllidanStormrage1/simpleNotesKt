package com.example.simplenotes.presentation.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenotes.R
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.utils.Consts.ITEM
import com.example.simplenotes.presentation.adapter.DataAdapter
import com.example.simplenotes.presentation.presenter.MainFragmentPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class MainFragment : MvpAppCompatFragment(),
    IMainFragmentView {

    @InjectPresenter
    lateinit var presenter: MainFragmentPresenter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_createNoteFragment) }
        presenter.itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) =
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> fab.show()
                    else -> fab.hide()
                }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun checkItemCountRV() {
        if (recyclerView.adapter?.itemCount != 0)
            hidePlaceholder()
        else
            showPlaceholder()
    }

    override fun setAdapter(adapter: DataAdapter) {
        recyclerView.adapter = adapter
    }

    private fun hidePlaceholder() {
        placeHolder.visibility = View.GONE
    }

    private fun showPlaceholder() {
        placeHolder.visibility = View.VISIBLE
    }

    override fun navigateToDetail(item: NoteItem) {
        val args = Bundle()
        args.putSerializable(ITEM, item)
        navController.navigate(R.id.action_mainFragment_to_detailNoteFragment, args)
    }

    override fun showUndoShackBar() =
        with(
            Snackbar.make(
                fab,
                resources.getString(R.string.item_deleted),
                Snackbar.LENGTH_SHORT
            )
        ) {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            show()
        }
}

