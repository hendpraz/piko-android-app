package com.hpdev.piko.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    var userId: Int,
    var fullName: String,
    var nickname: String,
    var mainCategory: String,
    var avatar: String,
    var mainContact: String,
    var isFavorite: Boolean
) : Parcelable