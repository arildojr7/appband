package com.arildojr.data.song

import androidx.paging.DataSource
import com.arildojr.data.song.datasource.local.SongLocalDataSource
import com.arildojr.data.song.datasource.remote.SongRemoteDataSource
import com.arildojr.data.song.model.Song

class SongRepositoryImpl(private val songRemoteDataSource: SongRemoteDataSource,
                         private val songLocalDataSource: SongLocalDataSource) : SongRepository {
    override suspend fun getSongs() : List<Song> {
        return songRemoteDataSource.getSongs()
    }

    override fun getAllPaged(): DataSource.Factory<Int, Song> {
        return songLocalDataSource.getAllPaged()
    }

    override fun insertAll(songs: List<Song>) {
        songLocalDataSource.insertAll(songs)
    }
}