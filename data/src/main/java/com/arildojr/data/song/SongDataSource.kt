package com.arildojr.data.song

import androidx.paging.DataSource
import com.arildojr.data.song.model.Song

interface SongDataSource {
    interface Local {
        fun getAllPaged(): DataSource.Factory<Int, Song>

        fun insertAll(songs: List<Song>)
    }

    interface Remote {
        suspend fun getSongs(): List<Song>
    }
}