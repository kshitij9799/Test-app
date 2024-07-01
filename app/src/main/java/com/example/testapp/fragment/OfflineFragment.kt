package com.example.testapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.adapter.OfflineDataAdapter
import com.example.testapp.model.Notes
import com.example.testapp.utils.OnItemClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class OfflineFragment : Fragment(), OnItemClickListener {

    val offlineViewModel: OfflineViewModel by viewModels()
    var NotesList: List<Notes> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_offline, container, false)

        val addBtn = view.findViewById<FloatingActionButton>(R.id.add_btn)
        val offlineTaskRecyclerview =
            view.findViewById<RecyclerView>(R.id.offline_task_recyclerview)

        addBtn.setOnClickListener {
            offlineViewModel.openAddFragment(this.parentFragmentManager)
        }


        CoroutineScope(Dispatchers.IO).launch {
            val getNotes = async { NotesList = offlineViewModel.readPersons() }
            CoroutineScope(Dispatchers.Main).launch {
                getNotes.await()
                offlineTaskRecyclerview.layoutManager = LinearLayoutManager(context)
                offlineTaskRecyclerview.adapter =
                    OfflineDataAdapter(this@OfflineFragment, NotesList,parentFragmentManager)
            }
        }

        return view
    }

    override fun onItemClick(
        note: Notes,
        isDeleteClicked: Boolean?,
        isUpdate: Boolean?,
        supportFragmentManager: FragmentManager?
    ) {
        if (isDeleteClicked != null) {
            if (isUpdate != null) {
                offlineViewModel.onItemClick(note, isDeleteClicked, isUpdate, supportFragmentManager)
            }
        }
    }

}