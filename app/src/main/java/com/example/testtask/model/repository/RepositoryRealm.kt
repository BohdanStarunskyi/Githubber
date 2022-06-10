package com.example.testtask.model.repository

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class RepositoryRealm : RealmObject {
    @PrimaryKey
    var url: String = ""
    var username: String = ""
    var repositoryName: String = ""
    var programmingLanguage: String? = ""
    var starCount: Int = 0
}
