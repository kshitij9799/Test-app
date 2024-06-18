package com.example.testapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.TodoApi
import com.example.testapp.adapter.OnlineDataAdapter
import com.example.testapp.model.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class OnlineFragment : Fragment() {
    val viewModel: OnlineViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_online, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.onlineRecyclerView)

        lifecycleScope.launch {
            viewModel.showData(recyclerView, requireContext())
        }

        return view
    }

}