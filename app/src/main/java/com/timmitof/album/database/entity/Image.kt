package com.timmitof.album.database.entity

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.timmitof.album.utils.BitmapConverter
import com.timmitof.album.utils.CoordinateConverter
import com.timmitof.album.utils.DateConverter
import java.util.Date

@Entity(tableName = "Image")
@TypeConverters(CoordinateConverter::class, DateConverter::class, BitmapConverter::class)
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val image: Bitmap?,
    val coordinate: Coordinate?,
    val date: Date?
)
