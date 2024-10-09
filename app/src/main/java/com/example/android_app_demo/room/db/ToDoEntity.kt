package com.example.android_app_demo.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDoData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "description") val desc: String,
    @ColumnInfo(name = "time") val time: String
)


