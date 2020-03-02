package com.arildojr.data.setlist.model

import android.os.Parcelable
import com.arildojr.data.song.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetList(
    var id: String? = null,
    var date: Long? = 0L,
    var song: List<Song> = emptyList()
) : Parcelable {

    fun songsNameList(): String {
        return this.song.map { it.title }.joinToString("\n")
    }

}