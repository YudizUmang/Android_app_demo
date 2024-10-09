package com.example.android_app_demo.room

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android_app_demo.R
import com.example.android_app_demo.room.db.ToDoData
import com.example.android_app_demo.room.db.ToDoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch

class ToDoList : AppCompatActivity() {
    private lateinit var db: ToDoDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var todoList: MutableList<ToDoData>
    private val latch = CountDownLatch(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        db = Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java, ToDoDatabase.DB_NAME
        ).build()

        setContentView(R.layout.activity_to_do_list)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
//        Thread {
                todoList = db.toDoDao().getAll()
//        }.start()

//                delay(2000)
                latch.countDown()
            }
        }
        latch.await()
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        toDoAdapter = ToDoAdapter(this, todoList)
        recyclerView.adapter = toDoAdapter

        toDoAdapter.onDeleteClick(object : ClickListener {
            override fun setListener(view: View, position: Int, todo: ToDoData) {
                deleteTodo(position, todo)
            }
        })

        toDoAdapter.onEditClick(object : ClickListener {
            override fun setListener(view: View, position: Int, todo: ToDoData) {
                val intent = Intent()
//                Log.d("desc", todo.desc.toString())
//                Log.d("id", todo.id.toString())
                intent.putExtra("todo", todo.desc)
                intent.putExtra("id", todo.id)
                intent.putExtra("type", "EDIT")
                setResult(RESULT_OK, intent)
                finish()
            }
        })


    }

    private fun deleteTodo(position: Int, toDo: ToDoData) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.toDoDao().deleteToDo(toDo)

            }
            todoList.removeAt(position)
            toDoAdapter.notifyItemRemoved(position)
        }
    }

}