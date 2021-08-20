package com.hpdev.piko.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "userId")
    var userId: Int,

    @ColumnInfo(name = "fullName")
    var fullName: String,

    @ColumnInfo(name = "nickname")
    var nickname: String,

    @ColumnInfo(name = "mainCategory")
    var mainCategory: String,

    @ColumnInfo(name = "avatar")
    var avatar: String,

    @ColumnInfo(name = "mainContact")
    var mainContact: String,

    @ColumnInfo(name = "dateAdded")
    var dateAdded: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable