package com.hpdev.piko.core.utils

import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.core.data.source.remote.response.UserResponse
import com.hpdev.piko.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                userId = it.userId,
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

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                userId = it.userId,
                fullName = it.fullName,
                nickname = it.nickname,
                mainCategory = it.mainCategory,
                avatar = it.avatar,
                mainContact = it.mainContact,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: User) = UserEntity(
        userId = input.userId,
        fullName = input.fullName,
        nickname = input.nickname,
        mainCategory = input.mainCategory,
        avatar = input.avatar,
        mainContact = input.mainContact,
        isFavorite = input.isFavorite
    )
}