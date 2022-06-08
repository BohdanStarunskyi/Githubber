package com.example.testtask.ui.main


import com.example.testtask.model.user.UserModel
import com.example.testtask.model.user.UserModelItem
import com.example.testtask.model.user.UserRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults


class UserDatabaseOperations {
    private val config = RealmConfiguration.Builder(schema = setOf(UserRealm::class)).build()
    private val realm = Realm.open(config)

    fun insertRepository(
        username: String,
        imageUrl: String,
        id: String
    ) {
        realm.writeBlocking {
            copyToRealm(UserRealm().apply {
                this.id = id
                this.username = username
                this.imageUrl = imageUrl
            })


        }
    }

    fun updateRepository(
        username: String,
        imageUrl: String
    ) {
        realm.writeBlocking {
            val user: UserRealm? = query<UserRealm>()
                .first()
                .find()
            user?.apply {
                this.username = username
                this.imageUrl = imageUrl
            }
        }
    }

    fun retrieveRepositories(): UserModel {
        val userModel = UserModel()
        val tasks: RealmResults<UserRealm> = realm.query<UserRealm>().find()
        val list = ArrayList<UserModelItem>()
        tasks.forEach { user ->
            list.add(
                UserModelItem(
                    login = user.username,
                    avatar_url = user.imageUrl
                )
            )
        }
        for (i in 0 until list.size) {
            userModel.add(list[i])
        }
        return userModel
    }

}