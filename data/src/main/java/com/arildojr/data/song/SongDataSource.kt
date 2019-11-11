package com.arildojr.data.song

import androidx.lifecycle.LiveData
import com.arildojr.data.song.model.Song

interface SongDataSource {
    interface Local {
        fun getSongs(): LiveData<List<Song>>

        fun getSongsFiltered(filter: String): LiveData<List<Song>>

        fun insertAll(songs: List<Song>)
    }

    interface Remote {
        suspend fun getSongs(): List<Song>
    }
}