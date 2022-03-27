package com.example.dogpics.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Photos::class], version = 1)
abstract class PicDataBase : RoomDatabase() {
    abstract fun photosDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: PicDataBase? = null
        fun getDataBase(context: Context): PicDataBase {
            synchronized((this)) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PicDataBase::class.java,
                        "PicDataBase"
                    ).build()
                    INSTANCE = instance
                }
                return instance

            }

        }
    }
}




