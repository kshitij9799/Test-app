package com.example.testapp.db

import android.app.Application
import com.example.testapp.model.Notes
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class TestApp : Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize Realm (adjust schema if needed)
        val config = RealmConfiguration.Builder(schema = setOf(Notes::class))
            .build()
        realm = Realm.open(config)
    }
}