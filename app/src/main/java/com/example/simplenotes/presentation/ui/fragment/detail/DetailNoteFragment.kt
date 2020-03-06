package com.example.simplenotes.presentation.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.simplenotes.R
import com.example.simplenotes.domain.entity.NoteItem
import com.example.simplenotes.domain.utils.Constants.ITEM
import com.example.simplenotes.presentation.presenter.DetailNoteFragmentPresenter
import kotlinx.android.synthetic.main.fragment_detail_note.*
import kotlinx.android.synthetic.main.input_block.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class DetailNoteFragment : MvpAppCompatFragment(), IDetailNoteFragmentView {

    @InjectPresenter
    lateinit var presenter: DetailNoteFragmentPresenter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.fragment_detail_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            setData(it.getSerializable(ITEM) as NoteItem)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setData(item: NoteItem) {
        titleEditText.setText(item.title, TextView.BufferType.NORMAL)
        descriptionEditText.setText(item.text, TextView.BufferType.NORMAL)

        fab.setOnClickListener {
            presenter.onFabPressed(
                item,
                titleEditText.text.toString(),
                descriptionEditText.text.toString()
            )
        }
    }

    override fun navigateToMainFragment() {
        navController.popBackStack()
    }
}
