package com.mobile.androiddicodingproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val name: String,
    val description: String,
    val photo: String
):Parcelable
