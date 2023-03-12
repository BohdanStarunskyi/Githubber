package com.example.testtask.domain.database_objects

import androidx.room.Entity
import com.example.testtask.common.constants.Constants

@Entity(tableName = Constants.REPOSITORIES_TABLE_NAME, primaryKeys = ["ownerId", "id"])
data class RepositoryDO(
    var ownerId: Int,
    var id: Int,
    var name: String? = null,
    var updatedAt: String? = null,
    var stargazersCount: Int? = null,
    var language: String? = null,
    var repoUrl: String? = null
)
