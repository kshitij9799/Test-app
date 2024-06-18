package com.example.testapp.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Notes : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var noteText: String = ""
}