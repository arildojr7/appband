package com.arildojr.data.song.datasource.local

import androidx.lifecycle.LiveData
import com.arildojr.data.song.SongDataSource
import com.arildojr.data.song.datasource.local.database.SongDatabase
import com.arildojr.data.song.model.Song

class SongLocalDataSource(private val songDatabase: SongDatabase) : SongDataSource.Local {
    override fun getSongs(): LiveData<List<Song>> {
        return songDatabase.songDao().getAll()
    }

    override fun getSongsFiltered(filter: String): LiveData<List<Song>> {
        return songDatabase.songDao().getAllFiltered(filter)
    }

    override fun insertAll(songs: List<Song>){
        return songDatabase.songDao().insertAll(songs)
    }
}