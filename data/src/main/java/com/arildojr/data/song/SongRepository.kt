package com.arildojr.data.song

import androidx.paging.DataSource
import com.arildojr.data.song.model.Song

interface SongRepository {

    suspend fun getSongs() : List<Song>

    fun getAllPaged(): DataSource.Factory<Int, Song>

    fun insertAll(songs: List<Song>)
}