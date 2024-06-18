package com.example.testapp.fragment


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.TodoApi
import com.example.testapp.adapter.OnlineDataAdapter
import com.example.testapp.model.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class OnlineViewModel : ViewModel() {

    suspend fun showData(recyclerView: RecyclerView,context: Context) {
        var todo: MutableList<Todo> = mutableListOf()
        var todos : Deferred<Unit>? = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                todos = async {
                    //get data from API
                    todo = TodoApi.retrofitService.getTodos().todos
                }
            } catch (e: Exception) {
                println("Error fetching todos: ${e.message}")
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            todos?.await()
            //updated recycler view
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = OnlineDataAdapter(todo)
        }
    }

}