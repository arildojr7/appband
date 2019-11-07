package com.arildojr.data.song.datasource.local

import androidx.paging.DataSource
import com.arildojr.data.song.SongDataSource
import com.arildojr.data.song.datasource.local.database.SongDatabase
import com.arildojr.data.song.model.Song

class SongLocalDataSource(private val songDatabase: SongDatabase) : SongDataSource.Local {
    override fun getAllPaged(): DataSource.Factory<Int, Song> {
        return songDatabase.songDao().getAllPaged()
    }

    override fun insertAll(songs: List<Song>){
        return songDatabase.songDao().insertAll(songs)
    }
}