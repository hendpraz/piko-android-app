package com.hpdev.piko.core.utils

import com.hpdev.piko.R
import com.hpdev.piko.core.data.source.local.entity.UserEntity
import com.hpdev.piko.home.HomeContactsFragment

fun generateEmptyFavorites() : MutableList<UserEntity> {
    val listUser = mutableListOf<UserEntity>()

    listUser.add(
        UserEntity(
            userId = HomeContactsFragment.EMPTY_FAVORITES_ID,
            fullName = "",
            nickname = "",
            mainCategory = "",
            avatar = "add",
            mainContact = ""
        )
    )

    return listUser
}