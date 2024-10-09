package com.example.android_app_demo.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoData::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object {
        const val DB_NAME = "todo_database"
    }
}
