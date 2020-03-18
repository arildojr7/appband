package dev.arildo.data.setlist.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.arildo.data.setlist.model.SetList
import kotlinx.coroutines.flow.Flow

@Dao
interface SetListDao {

    @Query("SELECT * FROM setlists")
    fun getAll(): Flow<List<SetList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(setLists: List<SetList>)

}