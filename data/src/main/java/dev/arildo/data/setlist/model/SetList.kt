package dev.arildo.data.setlist.model

import android.os.Parcelable
import dev.arildo.data.musician.model.Musician
import dev.arildo.data.song.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetList(
    var id: String? = null,
    var date: Long? = 0L,
    var number: Int? = null,
    var musician: List<Musician> = emptyList(),
    var song: List<Song> = emptyList()
) : Parcelable {

    fun songsNameList(): String {
        return this.song.map { it.title }.joinToString("\n")
    }

}