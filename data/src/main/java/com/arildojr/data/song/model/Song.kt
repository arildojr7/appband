package com.arildojr.data.song.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "songs")
data class Song(
    @PrimaryKey
    var id: String,
    val title: String = "",
    val singer: String = "",
    val thumb: String? = null
) : Parcelable