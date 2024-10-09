package com.example.android_app_demo.room.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo")
    suspend fun getAll(): MutableList<ToDoData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDoData: ToDoData)

    @Update
    suspend fun updateToDo(toDoData: ToDoData)

    @Delete
    suspend fun deleteToDo(toDoData: ToDoData)
}