package dev.arildo.data.musician.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "musicians")

data class Musician(
    @PrimaryKey
    val id: String = "",
    val name: String = ""
) : Parcelable