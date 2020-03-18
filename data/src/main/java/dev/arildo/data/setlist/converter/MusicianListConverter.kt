package dev.arildo.data.setlist.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.arildo.data.musician.model.Musician
import java.lang.reflect.Type
import java.util.*

class MusicianListConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Musician> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Musician?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Musician?>?): String {
        return gson.toJson(someObjects)
    }


}