package dev.arildo.data.song.datasource.local

import dev.arildo.data.song.SongDataSource
import dev.arildo.data.song.model.Song
import kotlinx.coroutines.flow.Flow

class SongLocalDataSource(private val songDao: SongDao) : SongDataSource.Local {
    override fun getSongs(): Flow<List<Song>> {
        return songDao.getAll()
    }

    override fun getSongsFiltered(filter: String): Flow<List<Song>> {
        return songDao.getAllFiltered(filter)
    }

    override fun insertAll(songs: List<Song>){
        return songDao.insertAll(songs)
    }
}