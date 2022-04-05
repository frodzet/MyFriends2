package com.example.myfriends2

import android.net.Uri
import java.util.*

data class Profile(
    var uuid: UUID = UUID.randomUUID(),
    var firstName: String = "Jens",
    var lastName: String = "Jensen",
    var age: Number = 30,
    var address: String = "Address 101, 8800 City",
    var phone: String = "+45 12 34 56 78",
    var imageURL: Uri? = null
)