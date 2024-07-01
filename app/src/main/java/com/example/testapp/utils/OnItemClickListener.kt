package com.example.testapp.utils

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.model.Notes

interface OnItemClickListener {
    fun onItemClick(note: Notes, isDeleteClicked: Boolean ?= false,
                    isUpdate: Boolean ?= false,
                    supportFragmentManager : FragmentManager ?= null)
}