package dev.arildo.data.song.datasource.local

import dev.arildo.data.song.SongDataSource
import dev.arildo.data.song.datasource.local.database.SongDatabase
import dev.arildo.data.song.model.Song
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