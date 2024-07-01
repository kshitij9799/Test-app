package com.example.testapp.fragment

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import com.example.testapp.R
import com.example.testapp.db.TestApp.Companion.realm
import com.example.testapp.model.Notes
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId

class UpdateDataViewModel : ViewModel() {

    fun updateEntry(id: ObjectId, updatedText: String, supportFragmentManager: FragmentManager?, context: Context) {

        realm.writeBlocking {
            val objectToUpdate = query<Notes>("id == $0", id).first().find()
            if (objectToUpdate != null) {
                objectToUpdate.noteText = updatedText
            }
        }
        Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show()
        supportFragmentManager?.commit {
            replace(R.id.fragment_container_view, OfflineFragment())
        }
    }
}