package com.example.testtask.ui.detail

import com.example.testtask.model.repository.RepositoryModel
import com.example.testtask.model.repository.RepositoryModelItem
import com.example.testtask.model.repository.RepositoryRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

open class RepositoryDatabaseOperations {

    private val config = RealmConfiguration.Builder(schema = setOf(RepositoryRealm::class)).build()
    private val realm = Realm.open(config)

    fun insertRepository(
        id: String,
        repositoryName: String,
        programmingLanguage: String?,
        starCount: Int,
        url: String
    ) {
        realm.writeBlocking {
            copyToRealm(RepositoryRealm().apply {
                this.id = id
                this.repositoryName = repositoryName
                this.programmingLanguage = programmingLanguage
                this.starCount = starCount
                this.url = url
            })


        }
    }

    fun updateRepository(
        id: String,
        repositoryName: String,
        programmingLanguage: String?,
        starCount: Int,
        url: String
    ) {
        realm.writeBlocking {
            val user: RepositoryRealm? =  query<RepositoryRealm>("id == $0", id).first().find()
            user?.apply {
                this.repositoryName = repositoryName
                this.programmingLanguage = programmingLanguage
                this.starCount = starCount
                this.url = url
            }
        }
    }

    fun retrieveRepositories(): RepositoryModel {
        val repositoryModel = RepositoryModel()
        val tasks: RealmResults<RepositoryRealm> = realm.query<RepositoryRealm>().find()
        val list = ArrayList<RepositoryModelItem>()
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
        for (i in 0 until list.size) {
            repositoryModel.add(list[i])
        }
        return repositoryModel
    }

}