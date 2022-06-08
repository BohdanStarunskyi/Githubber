package com.example.testtask.ui.main

import com.example.testtask.model.user.UserModelItem
import com.example.testtask.model.user.UserRealm


import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults


class UserDatabaseOperations{
    suspend fun insertRepository(
        username: String,
        imageUrl: String,
        id: String
    ) {
        val config = RealmConfiguration.Builder(schema = setOf(UserRealm::class))
            .build()
        val realm: Realm = Realm.open(config)
        realm.write {
            copyToRealm(UserRealm().apply {
                this.id = id
                this.username = username
                this.imageUrl = imageUrl
            })


        }
    }

    suspend fun updateRepository(username: String,
                                 imageUrl: String){
        val config = RealmConfiguration.Builder(schema = setOf(UserRealm::class))
            .build()
        val realm = Realm.open(config)
        realm.write {
            val user: UserRealm? = query<UserRealm>()
                .first()
                .find()
            // update the frog's properties
            user?.apply {
                this.username = username
                this.imageUrl = imageUrl
            }
        } // when the transaction completes, the frog's name and species
// are updated in the database
    }

    fun retrieveRepositories(): ArrayList<UserModelItem> {
        val config = RealmConfiguration.Builder(schema = setOf(UserRealm::class))
            .build()
        val realm: Realm = Realm.open(config)
        val tasks: RealmResults<UserRealm> = realm.query<UserRealm>().find()
        val list = arrayListOf<UserModelItem>()
        tasks.forEach { user ->
            list.add(
                UserModelItem(
                    login = user.username,
                    avatar_url = user.imageUrl
                )
            )
        }
        return list
    }

}