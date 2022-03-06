package com.example.myapplication3.DataBase

import android.icu.text.CaseMap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "todo_Items")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) var id : Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content : String
)