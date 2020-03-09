package dev.arildo.data.song.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "songs")
data class Song(
    @PrimaryKey
    var id: String,
    val title: String? = null,
    val singer: String? = null,
    val tone: String? = null,
    val capo: Int? = null,
    val chord: String? = null,
    val thumb: String? = null
) : Parcelable