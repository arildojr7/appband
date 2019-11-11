package com.arildojr.data.song

import androidx.lifecycle.LiveData
import com.arildojr.data.song.datasource.local.SongLocalDataSource
import com.arildojr.data.song.datasource.remote.SongRemoteDataSource
import com.arildojr.data.song.model.Song

class SongRepositoryImpl(private val songRemoteDataSource: SongRemoteDataSource,
                         private val songLocalDataSource: SongLocalDataSource) : SongRepository {
    override fun getSongs() : LiveData<List<Song>> {
        return songLocalDataSource.getSongs()
    }

    override fun getSongsFiltered(filter: String): LiveData<List<Song>> {
        return songLocalDataSource.getSongsFiltered(filter)
    }

    override fun insertAll(songs: List<Song>) {
        songLocalDataSource.insertAll(songs)
    }
}