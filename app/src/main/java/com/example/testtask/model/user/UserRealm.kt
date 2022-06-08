package com.example.testtask.model.user

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

 open class UserRealm: RealmObject{
     @PrimaryKey
     var id: String = ""
     var username: String = ""
     var imageUrl: String = ""
 }

