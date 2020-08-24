package com.dicoding.picodiploma.githubuserappv2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String = "",
    var username: String = "",
    var company: String = "",
    var location: String = "",
    var avatar: String = "",
    var repository: String = "",
    var follower: String = "",
    var following: String = ""
) : Parcelable