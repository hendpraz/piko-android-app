package com.hpdev.piko.utils

import com.hpdev.piko.R
import com.hpdev.piko.data.UserEntity
import com.hpdev.piko.ui.home.HomeContactsFragment

fun generateEmptyFavorites() : MutableList<UserEntity> {
    val listUser = mutableListOf<UserEntity>()

    listUser.add(
        UserEntity(
            id = HomeContactsFragment.EMPTY_FAVORITES_ID,
            fullName = "",
            nickname = "",
            mainCategory = "",
            avatar = R.drawable.add_favorite,
            mainContact = ""
        )
    )

    return listUser
}

fun getUserById(id: Int) : UserEntity {
    val listUser = generateDummyUsers()

    return listUser[id]
}

fun getAllUsers() : MutableList<UserEntity> {
    return generateDummyUsers()
}

fun getTopRecentUsers() : MutableList<UserEntity> {
    val listUser = generateDummyUsers()
    val users = mutableListOf<UserEntity>()

    users.add(listUser[0])
    users.add(listUser[1])
    users.add(listUser[2])
    users.add(listUser[3])
    users.add(listUser[4])

    return users
}

fun getAllFavoriteUsers() : MutableList<UserEntity> {
    val listUser = generateDummyUsers()
    val users = mutableListOf<UserEntity>()

    users.add(listUser[0])
    users.add(listUser[4])
    users.add(listUser[5])
    users.add(listUser[6])

    return users
}

fun getTopFavoriteUsers() : MutableList<UserEntity> {
    val listUser = generateDummyUsers()
    val users = mutableListOf<UserEntity>()

    users.add(listUser[0])
    users.add(listUser[1])
    users.add(listUser[4])
    users.add(listUser[5])

    return users
}

fun getRecentUsers() : MutableList<UserEntity> {
    val listUser = generateDummyUsers()
    val recentUser = mutableListOf<UserEntity>()

    recentUser.add(listUser[0])
    recentUser.add(listUser[1])
    recentUser.add(listUser[2])

    return recentUser
}

private fun generateDummyUsers() : MutableList<UserEntity> {
    val listUser = mutableListOf<UserEntity>()

    listUser.add(
        UserEntity(
            id = 0,
            fullName = "Hendry Prasetya",
            nickname = "Hendry",
            mainCategory = "College Friends",
            avatar = R.drawable.man_1,
            mainContact = "@hendryprasetyaa (IG)"
        )
    )

    listUser.add(
        UserEntity(
            id = 1,
            fullName = "Vian Aldi",
            nickname = "Bian Barudi",
            mainCategory = "HighSchool Friends",
            avatar = R.drawable.man_2,
            mainContact = "@vian_aldi (IG)"
        )
    )

    listUser.add(
        UserEntity(
            id = 2,
            fullName = "Mr. Pambudi Luhut",
            nickname = "Pak Pam",
            mainCategory = "Lecturer",
            avatar = R.drawable.man_3,
            mainContact = "@pambudi_lht (IG)"
        )
    )

    listUser.add(
        UserEntity(
            id = 3,
            fullName = "Khairul Akmal",
            nickname = "BAKWO CAK KHAIRUL",
            mainCategory = "Family",
            avatar = R.drawable.man_4,
            mainContact = "0812-2345-2312 (WA)"
        )
    )

    listUser.add(
        UserEntity(
            id = 4,
            fullName = "Marechiyo Omaeda",
            nickname = "Omaeda",
            mainCategory = "Others",
            avatar = R.drawable.man_5,
            mainContact = "0814-2325-2312 (WA)"
        )
    )

    listUser.add(
        UserEntity(
            id = 5,
            fullName = "Katarina Devon",
            nickname = "Karina",
            mainCategory = "Friend",
            avatar = R.drawable.woman_1,
            mainContact = "@karina_devon (IG)"
        )
    )

    listUser.add(
        UserEntity(
            id = 6,
            fullName = "Jennie Ruby Jane",
            nickname = "Jennie",
            mainCategory = "Friend",
            avatar = R.drawable.woman_2,
            mainContact = "@jennierubyjane (IG)"
        )
    )

    listUser.add(
        UserEntity(
            id = 7,
            fullName = "John Mayer",
            nickname = "John",
            mainCategory = "Friend",
            avatar = R.drawable.man_6,
            mainContact = "0814-2325-2442 (WA)"
        )
    )

    return listUser
}