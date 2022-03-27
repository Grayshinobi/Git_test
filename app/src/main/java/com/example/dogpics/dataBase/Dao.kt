package com.example.dogpics.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM photos_table")
    fun getAll(): LiveData<List<Photos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(photos: Photos)

    /*  @Insert(onConflict = OnConflictStrategy.IGNORE)
      suspend fun insertData(photos: List<Photos>)
  */
}