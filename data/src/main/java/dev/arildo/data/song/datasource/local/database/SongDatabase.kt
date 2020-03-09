package dev.arildo.data.song.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.arildo.data.song.datasource.local.SongDao
import dev.arildo.data.song.model.Song

@Database(
    entities = [Song::class],
    version = 1
)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao

    companion object {
        @Volatile
        private var instance: SongDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            SongDatabase::class.java, "songs-database.db"
        ).build()
    }

}