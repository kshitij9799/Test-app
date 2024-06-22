package com.example.testapp.fragment

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
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

    fun onItemClick(note: Notes,isDelete:Boolean) {
        if (isDelete) deleteEntry(note.id)
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