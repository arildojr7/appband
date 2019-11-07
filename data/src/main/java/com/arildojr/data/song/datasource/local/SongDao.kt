package com.arildojr.data.song.datasource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arildojr.data.song.model.Song

@Dao
interface SongDao {
    @Query("SELECT * FROM songs")
    fun getAll(): LiveData<List<Song>>

    @Query("SELECT * FROM songs")
    fun getAllPaged(): DataSource.Factory<Int, Song>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(songs: List<Song>)
}