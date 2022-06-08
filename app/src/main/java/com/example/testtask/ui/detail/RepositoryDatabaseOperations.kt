package com.example.testtask.ui.detail

import com.example.testtask.model.repository.RepositoryModelItem
import com.example.testtask.model.repository.RepositoryRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

open class RepositoryDatabaseOperations {

    suspend fun insertRepository(
        repositoryName: String,
        programmingLanguage: String,
        starCount: Int,
        url: String
    ) {
        val config = RealmConfiguration.Builder(schema = setOf(RepositoryRealm::class))
            .build()
        val realm: Realm = Realm.open(config)
        realm.write {
            copyToRealm(RepositoryRealm().apply {
                this.repositoryName = repositoryName
                this.programmingLanguage = programmingLanguage
                this.starCount = starCount
                this.url = url
            })
        }
    }

    fun retrieveRepositories(): ArrayList<RepositoryModelItem> {
        val config = RealmConfiguration.Builder(schema = setOf(RepositoryRealm::class))
            .build()
        val realm: Realm = Realm.open(config)
        val tasks: RealmResults<RepositoryRealm> = realm.query<RepositoryRealm>().find()
        val list = arrayListOf<RepositoryModelItem>()
        tasks.forEach { repository ->
            list.add(
                RepositoryModelItem(
                    name = repository.repositoryName,
                    language = repository.programmingLanguage,
                    stargazers_count = repository.starCount,
                    html_url = repository.url
                )
            )
        }
        return list
    }
}