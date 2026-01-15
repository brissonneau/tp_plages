package biz.ei6.eluvplages.datasource

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun listToString(value: List<String>): String =
        value.joinToString(separator = ",")

    @TypeConverter
    fun stringToList(value: String): List<String> =
        if (value.isBlank()) emptyList() else value.split(",")
}
