package com.example.testtask.model.repository

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class RepositoryRealm : RealmObject {
    @PrimaryKey
    var id: String = ""
    var username: String = ""
    var repositoryName: String = ""
    var programmingLanguage: String? = ""
    var starCount: Int = 0
    var url: String = ""
}
