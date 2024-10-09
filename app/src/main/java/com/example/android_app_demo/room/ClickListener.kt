package com.example.android_app_demo.room

import android.view.View
import com.example.android_app_demo.room.db.ToDoData

interface ClickListener {
    fun setListener(view: View, position: Int, todo: ToDoData)
}