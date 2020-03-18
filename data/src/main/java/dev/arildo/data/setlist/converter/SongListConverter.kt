package dev.arildo.data.setlist.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.arildo.data.song.model.Song
import java.lang.reflect.Type
import java.util.*

class SongListConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Song> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Song?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Song?>?): String {
        return gson.toJson(someObjects)
    }

}