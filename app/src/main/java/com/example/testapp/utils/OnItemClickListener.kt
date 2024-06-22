package com.example.testapp.utils

import com.example.testapp.model.Notes

interface OnItemClickListener {
    fun onItemClick(note: Notes,isDeleteClicked:Boolean)
}