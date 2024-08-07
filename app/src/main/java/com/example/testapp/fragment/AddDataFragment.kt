package com.example.testapp.fragment

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.testapp.R

class AddDataFragment : Fragment() {
    private val viewModel: AddDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_add_data, container, false)
        val nameEditText = view.findViewById<EditText>(R.id.task)
        val button = view.findViewById<Button>(R.id.submit_btn)
        button.setOnClickListener {
            context?.let { viewModel.submitData(it,nameEditText.text.toString()) }
            Toast.makeText(requireContext(), "Added successfully", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragment_container_view, OfflineFragment())
            }
        }
        return view
    }
}