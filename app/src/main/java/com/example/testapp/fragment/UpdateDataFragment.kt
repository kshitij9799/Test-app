package com.example.testapp.fragment

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testapp.MainActivity
import com.example.testapp.R
import com.example.testapp.model.Notes

class UpdateDataFragment(note: Notes) : Fragment() {

    private val viewModel: UpdateDataViewModel by viewModels()
    private val oldNote: Notes = note
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_update_data, container, false)

        val task = view.findViewById<EditText>(R.id.task_edittext)
        val updateBtn = view.findViewById<Button>(R.id.update_btn)

        task.setText(oldNote.noteText)

        updateBtn.setOnClickListener {
            viewModel.updateEntry(oldNote.id, task.text.toString(), parentFragmentManager, requireContext())
        }

        return view
    }
}