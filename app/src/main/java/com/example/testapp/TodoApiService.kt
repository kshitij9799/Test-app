package com.example.testapp

import com.example.testapp.model.Todo
import com.example.testapp.model.TodoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): TodoResponse

    @Headers("Content-Type: application/json","Host: <calculated when request is sent>")
    @PUT("todos/{id}")
    suspend fun updateTodo(
        @Path("id") id: Int ?= 1,
        @Body completedStatus: Boolean ? = true
    ): Todo

}