package com.example.testapp.fragment

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import com.example.testapp.MainActivity
import com.example.testapp.R
import com.example.testapp.db.TestApp
import com.example.testapp.model.Notes
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

class OfflineViewModel :ViewModel() {
    val realm = TestApp.realm

    fun openAddFragment(supportFragmentManager: FragmentManager) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, AddDataFragment())
        }
    }

    suspend fun readPersons() : List<Notes> {
        var allNotes: List<Notes>
        withContext(Dispatchers.IO) {
            allNotes = realm.query(Notes::class).find()
            for (person in allNotes as RealmResults<Notes>) {
                println("(1) 12221Name1: ${person.noteText}, ID: ${person.id}")
            }
        }
        return allNotes
    }

    fun onItemClick(note: Notes,
                    isDelete:Boolean,
                    isUpdate:Boolean,
                    supportFragmentManager: FragmentManager ?= null) {
        if (isDelete) {
            deleteEntry(note.id)
            supportFragmentManager?.commit {
                replace(R.id.fragment_container_view, OfflineFragment())
            }
        }

        else if (isUpdate) {
            openUpdateFragment(note, supportFragmentManager)
        }
    }

    private fun openUpdateFragment(note: Notes, supportFragmentManager: FragmentManager?) {
        supportFragmentManager?.commit {
            replace(R.id.fragment_container_view, UpdateDataFragment(note))
        }
    }


    fun deleteEntry(id:ObjectId) {

        realm.writeBlocking {
            Log.d("checkOut", "DeleteEntry: $id")
            // Find the object with the matching ObjectId
            val objectToDelete = query<Notes>("id == $0", id).first().find()

            // Delete the object if found
            if (objectToDelete != null) {
                delete(objectToDelete)
            }
        }
    }
}