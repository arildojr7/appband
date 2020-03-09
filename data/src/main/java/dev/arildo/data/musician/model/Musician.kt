package dev.arildo.data.musician.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Musician(
    val id: String = "",
    val name: String = ""
) : Parcelable