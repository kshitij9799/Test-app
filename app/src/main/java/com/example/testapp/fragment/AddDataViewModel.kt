package com.example.testapp.fragment

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.testapp.db.TestApp
import com.example.testapp.model.Notes
import io.realm.kotlin.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddDataViewModel : ViewModel() {
    fun submitData(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

        val realm = TestApp.realm // Assuming 'config' is your RealmConfiguration

        insertPerson(realm, text)
    }

    fun insertPerson(realm: Realm, note: String) {
        realm.writeBlocking {
            copyToRealm(Notes().apply {
                this.noteText = note
            })
        }
    }



}