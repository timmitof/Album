package com.timmitof.album.database.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.timmitof.album.utils.CoordinateConverter
import com.timmitof.album.utils.DateConverter
import java.util.Date

@Entity(tableName = "Image")
@TypeConverters(CoordinateConverter::class, DateConverter::class)
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val url: Uri?,
    val coordinate: Coordinate?,
    val date: Date?
)
