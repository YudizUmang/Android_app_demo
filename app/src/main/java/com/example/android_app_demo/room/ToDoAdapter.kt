package com.example.android_app_demo.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app_demo.R
import com.example.android_app_demo.room.db.ToDoData

class ToDoAdapter(
    val context: Context,
    var todoList: MutableList<ToDoData>,
) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    private lateinit var editListener: ClickListener
    private lateinit var deleteListener: ClickListener

    class ToDoViewHolder(todoView: View) : RecyclerView.ViewHolder(todoView) {
        val todoDescTextView: TextView = todoView.findViewById(R.id.todo_desc)
        val editBtn: Button = todoView.findViewById(R.id.edit_todo_btn)
        val deleteBtn: Button = todoView.findViewById(R.id.delete_todo_btn)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_holder, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int = todoList.size


    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.todoDescTextView.text = todo.desc
        holder.editBtn.setOnClickListener {
            editListener.setListener(holder.itemView, position, todoList[position])
        }

        holder.deleteBtn.setOnClickListener {
            deleteListener.setListener(holder.itemView, position, todoList[position])
        }
    }

    fun onEditClick(listener: ClickListener) {
        this.editListener = listener
    }

    fun onDeleteClick(listener: ClickListener) {
        this.deleteListener = listener
    }
}