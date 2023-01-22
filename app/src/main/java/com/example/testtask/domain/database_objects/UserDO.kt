package com.example.testtask.domain.database_objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtask.common.Constants

@Entity(tableName = Constants.USERS_TABLE_NAME)
data class UserDO(
    @PrimaryKey
    var id: Int,
    var username: String? = null,
    var avatarUrl: String? = null
)
