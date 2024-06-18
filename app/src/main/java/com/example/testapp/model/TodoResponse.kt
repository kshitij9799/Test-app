package com.example.testapp.model
import androidx.lifecycle.LiveData
import kotlinx.serialization.Serializable

@Serializable
data class TodoResponse(
    val todos: MutableList<Todo>
)