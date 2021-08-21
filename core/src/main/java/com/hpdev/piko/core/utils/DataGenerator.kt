package com.hpdev.piko.core.utils

import com.hpdev.piko.core.domain.model.User

const val EMPTY_FAVORITES_ID = -999

fun generateEmptyFavorites() : MutableList<User> {
    val listUser = mutableListOf<User>()

    listUser.add(
        User(
            userId = EMPTY_FAVORITES_ID,
            fullName = "",
            nickname = "",
            mainCategory = "",
            avatar = "add",
            mainContact = "",
            dateAdded = "",
            isFavorite = false
        )
    )

    return listUser
}