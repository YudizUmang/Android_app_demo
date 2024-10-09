package com.example.android_app_demo.room

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.android_app_demo.R
import com.example.android_app_demo.room.db.ToDoData
import com.example.android_app_demo.room.db.ToDoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ToDoMain : AppCompatActivity() {
    private lateinit var db: ToDoDatabase
    private lateinit var todoEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var showAllTodo: Button
    private var operationType: String = "ADD"
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        db = Room.databaseBuilder(
            applicationContext,
            ToDoDatabase::class.java, ToDoDatabase.DB_NAME
        ).build()
        setContentView(R.layout.activity_to_do)
        todoEditText = findViewById(R.id.todo_description)
        saveButton = findViewById(R.id.save_btn)
        showAllTodo = findViewById(R.id.showall_todo)



        saveButton.setOnClickListener {
            if (operationType == "EDIT") {
                val current = LocalDateTime.now()
                val formatted =
                    current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                val todo = ToDoData(id, todoEditText.text.toString(), formatted)
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { db.toDoDao().updateToDo(todo) }
                }
                todoEditText.text.clear()
                operationType = "ADD"

            } else {
                val current = LocalDateTime.now()
                val formatted =
                    current.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                val todo = ToDoData(0, todoEditText.text.toString(), formatted)
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { db.toDoDao().insertToDo(todo) }
                }
                todoEditText.text.clear()
            }
        }

        val getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                if (result.data != null) {
                    val value = result.data!!.getStringExtra("todo")
                    operationType = result.data!!.getStringExtra("type").toString()
                    id = result.data!!.getIntExtra("id", 0)
                    todoEditText.setText(value)
                }
            }
        }

        showAllTodo.setOnClickListener {
            val intent = Intent(this, ToDoList::class.java)
            getResult.launch(intent)
        }
    }
}
