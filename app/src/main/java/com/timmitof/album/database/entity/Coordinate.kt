package com.timmitof.album.database.entity

import androidx.room.Entity

@Entity(tableName = "Coordinate")
data class Coordinate(
    val longitude: Float?,
    val latitude: Float?
)
