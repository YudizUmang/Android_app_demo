package com.example.android_app_demo.recyclerview.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app_demo.R
import com.example.android_app_demo.recyclerview.adapter.PersonAdapter
import com.example.android_app_demo.recyclerview.model.Person

class RecyclerViewMain : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var personAdapter: PersonAdapter
    val personList = listOf(
        Person("Raj", "1234567890", "Raj.k@abc.com", "New York"),
        Person("Umang", "0987654321", "umang.c@think.lab", "Rajkot"),
        Person("Kishan", "1234567890", "Kishan@abc.com", "New York"),
        Person("Krish", "0987654321", "krish.c@cube.com", "Ahmedabad"),
        Person("Raj", "1234567890", "Raj.k@abc.com", "New York"),
        Person("Umang", "0987654321", "umang.c@think.lab", "Rajkot"),
        Person("Kishan", "1234567890", "Kishan@abc.com", "New York"),
        Person("Krish", "0987654321", "krish.c@cube.com", "Ahmedabad"),
        Person("Raj", "1234567890", "Raj.k@abc.com", "New York"),
        Person("Umang", "0987654321", "umang.c@think.lab", "Rajkot"),
        Person("Kishan", "1234567890", "Kishan@abc.com", "New York"),
        Person("Krish", "0987654321", "krish.c@cube.com", "Ahmedabad"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        personAdapter = PersonAdapter(this, personList)
        recyclerView.adapter = personAdapter
    }
}
