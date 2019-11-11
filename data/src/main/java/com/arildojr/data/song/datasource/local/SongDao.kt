package com.arildojr.data.song.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arildojr.data.song.model.Song

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY title")
    fun getAll(): LiveData<List<Song>>

        @Query("SELECT * FROM songs WHERE title LIKE :filter")
    fun getAllFiltered(filter: String): LiveData<List<Song>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(songs: List<Song>)
}