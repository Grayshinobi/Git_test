package com.example.dogpics.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos_table")
data class Photos(
    @PrimaryKey(autoGenerate = true)
    val randomId: Int,
    @ColumnInfo(name = "photoUrl")
    val photoUrl: String,
    @ColumnInfo(name = "Breed")
    val breed: String,
)
