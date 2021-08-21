package com.hpdev.piko.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @field:SerializedName("id")
    var userId: Int,

    @field:SerializedName("full_name")
    var fullName: String,

    @field:SerializedName("nickname")
    var nickname: String,

    @field:SerializedName("main_category")
    var mainCategory: String,

    @field:SerializedName("avatar")
    var avatar: String,

    @field:SerializedName("main_contact")
    var mainContact: String,

    @field:SerializedName("date_added")
    var dateAdded: String
)