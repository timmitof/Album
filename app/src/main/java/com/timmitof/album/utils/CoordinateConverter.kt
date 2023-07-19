package com.timmitof.album.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.timmitof.album.database.entity.Coordinate

class CoordinateConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCoordinate(coordinate: Coordinate?): String = gson.toJson(coordinate)

    @TypeConverter
    fun toCoordinate(json: String?): Coordinate = gson.fromJson(json, Coordinate::class.java)
}