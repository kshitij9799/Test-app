package com.example.testapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.db.TestApp
import com.example.testapp.model.Notes
import com.example.testapp.model.Todo
import io.realm.kotlin.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val realm = TestApp.realm // Assuming 'config' is your RealmConfiguration

        insertPerson(realm, "Alice")
        insertPerson(realm, "Bob")
        insertPerson(realm, "Kshitij")
        insertPerson(realm, "Anushka")

        CoroutineScope(Dispatchers.IO).launch {
            readPersons(realm)
        }
    }

    suspend fun readPersons(realm: Realm) {
        withContext(Dispatchers.IO) {
            val allPersons = realm.query(Notes::class).find()
            for (person in allPersons) {
                println("(1) 12221Name1: ${person.noteText}, ID: ${person.id}")
            }
        }
    }

    fun insertPerson(realm: Realm, name: String) {
        realm.writeBlocking {
            copyToRealm(Notes().apply {
                this.noteText = name
            })
        }
    }
}