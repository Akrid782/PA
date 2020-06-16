package com.diplom.pa.models

data class UserModel(
    var id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var state: String = "",
    var phone: String = "",
    var email: String = "",
    var photoUrl: String = "empty"
)