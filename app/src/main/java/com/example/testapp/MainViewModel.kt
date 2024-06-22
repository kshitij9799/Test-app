package com.example.testapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.Notes
import com.example.testapp.model.Todo
import io.realm.kotlin.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
//    private val realm = MyApp.realm

//    val courseList = realm.query<Address>().asFlow().map { result -> result.list.toList() }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

}