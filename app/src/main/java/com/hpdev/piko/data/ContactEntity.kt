package com.hpdev.piko.data

data class ContactEntity (
    var id: Int,
    var username: String,
    var typeCode: String,
    var isMain: Boolean = false
)