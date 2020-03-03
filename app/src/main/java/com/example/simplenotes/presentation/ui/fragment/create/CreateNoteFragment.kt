package com.example.simplenotes.presentation.ui.fragment.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.simplenotes.R
import com.example.simplenotes.presentation.presenter.CreateNoteFragmentPresenter
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.android.synthetic.main.input_block.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CreateNoteFragment : MvpAppCompatFragment(), ICreateNoteFragmentView {

    @InjectPresenter
    lateinit var presenter: CreateNoteFragmentPresenter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            presenter.onFabPressed(
                titleEditText.text.toString(), descriptionEditText.text.toString()
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun navigateToMainFragment() {
        navController.popBackStack()
    }

}
