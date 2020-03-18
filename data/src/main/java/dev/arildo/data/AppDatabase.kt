package dev.arildo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.arildo.data.musician.model.Musician
import dev.arildo.data.setlist.converter.MusicianListConverter
import dev.arildo.data.setlist.converter.SongListConverter
import dev.arildo.data.setlist.datasource.local.SetListDao
import dev.arildo.data.setlist.model.SetList
import dev.arildo.data.song.datasource.local.SongDao
import dev.arildo.data.song.model.Song

@Database(
    entities = [Song::class, SetList::class, Musician::class],
    version = 1
)
@TypeConverters(MusicianListConverter::class, SongListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao

    abstract fun setListDao(): SetListDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(context)
                        .also { instance = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "app-database.db"
        ).build()
    }

}