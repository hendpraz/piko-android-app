package com.hpdev.piko.core.data.source.remote.response

data class UserResponse (
    var userId: Int,
    var fullName: String,
    var nickname: String,
    var mainCategory: String,
    var avatar: String,
    var mainContact: String
)