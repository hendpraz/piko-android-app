package com.hpdev.piko.core.utils

import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.core.data.source.remote.response.UserResponse

object DataMapper {
    fun mapResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                userId = it.id,
                fullName = it.fullName,
                nickname = it.nickname,
                mainCategory = it.mainCategory,
                avatar = it.avatar,
                mainContact = it.mainContact,
                isFavorite = false
            )
            userList.add(user)
        }
        return userList
    }
}