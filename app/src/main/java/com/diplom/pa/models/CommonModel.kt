package com.diplom.pa.models

data class CommonModel (
    var id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var state: String = "",
    var phone: String = "",
    var email: String = "",
    var photoUrl: String = "empty",

    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: Any = "",
    var fileUrl: String = "empty"
) {
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).id == id
    }
}