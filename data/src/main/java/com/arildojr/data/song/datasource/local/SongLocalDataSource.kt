package com.arildojr.data.song.datasource.local

import com.arildojr.data.song.SongDataSource
import com.arildojr.data.song.datasource.local.database.SongDatabase
import com.arildojr.data.song.model.Song
import kotlinx.coroutines.flow.Flow

class SongLocalDataSource(private val songDatabase: SongDatabase) : SongDataSource.Local {
    override fun getSongs(): Flow<List<Song>> {
        return songDatabase.songDao().getAll()
    }

    override fun getSongsFiltered(filter: String): Flow<List<Song>> {
        return songDatabase.songDao().getAllFiltered(filter)
    }

    override fun insertAll(songs: List<Song>){
        return songDatabase.songDao().insertAll(songs)
    }
}