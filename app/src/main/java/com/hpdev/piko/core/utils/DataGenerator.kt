package com.hpdev.piko.core.utils

import com.hpdev.piko.core.domain.model.User
import com.hpdev.piko.home.HomeContactsFragment

fun generateEmptyFavorites() : MutableList<User> {
    val listUser = mutableListOf<User>()

    listUser.add(
        User(
            userId = HomeContactsFragment.EMPTY_FAVORITES_ID,
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