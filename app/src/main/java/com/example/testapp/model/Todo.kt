package com.example.testapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("todo")
    @Expose
    val todo: String,
    @SerializedName("completed")
    @Expose
    val completed: Boolean,
    @SerializedName("userId")
    @Expose
    val userId: Int
)
