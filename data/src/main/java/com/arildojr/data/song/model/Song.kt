package com.arildojr.data.song.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey
    var id: String,
    val title: String = "",
    val singer: String = "",
    val thumb: String? = null
)