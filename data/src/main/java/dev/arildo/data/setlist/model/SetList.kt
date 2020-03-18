package dev.arildo.data.setlist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.arildo.data.musician.model.Musician
import dev.arildo.data.song.model.Song
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "setlists")
data class SetList(
    @PrimaryKey
    var id: String = "",
    var date: Long? = 0L,
    var number: Int? = null,
    var musician: List<Musician> = emptyList(),
    var song: List<Song> = emptyList()
) : Parcelable {

    fun songsNameList(): String {
        return this.song.map { it.title }.joinToString("\n")
    }

}