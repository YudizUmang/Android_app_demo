package com.example.android_app_demo.menu

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app_demo.R
import com.example.android_app_demo.menu.model.MenuPerson
import com.example.android_app_demo.recyclerview.adapter.PersonCardAdapter

class MenuDemo : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var personCardAdapter: PersonCardAdapter
    private lateinit var btnContext: Button
    private val personList = listOf(
        MenuPerson("Raj", "New York"),
        MenuPerson("Umang", "Rajkot"),
        MenuPerson("Kishan", "New York"),
        MenuPerson("Krish", "Ahmedabad"),
        MenuPerson("Raj", "New York"),
        MenuPerson("Umang", "Rajkot"),
        MenuPerson("Kishan", "New York"),
        MenuPerson("Krish", "Ahmedabad"),
        MenuPerson("Raj", "New York"),
        MenuPerson("Umang", "Rajkot"),
        MenuPerson("Kishan", "New York"),
        MenuPerson("Krish", "Ahmedabad")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu_demo)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.menu_demo_toolbar)
        setSupportActionBar(toolbar)

        btnContext = findViewById(R.id.btn_ctx)
        recyclerView = findViewById(R.id.menu_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        personCardAdapter = PersonCardAdapter(this, personList)
        recyclerView.adapter = personCardAdapter

        // Register for context menu on the button
        registerForContextMenu(btnContext)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.info -> {
                Toast.makeText(this, "Information", Toast.LENGTH_LONG).show()
                true
            }

            R.id.create_new -> {
                Toast.makeText(this, "Create new", Toast.LENGTH_LONG).show()
                true
            }

            R.id.save -> {
                Toast.makeText(this, "Save", Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu?.apply {
            setHeaderTitle("Choose a color")
            add(0, v?.id ?: View.NO_ID, 0, "Yellow")
            add(0, v?.id ?: View.NO_ID, 0, "Gray")
            add(0, v?.id ?: View.NO_ID, 0, "Cyan")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "Yellow" -> btnContext.setBackgroundColor(Color.YELLOW)
            "Gray" -> btnContext.setBackgroundColor(Color.GRAY)
            "Cyan" -> btnContext.setBackgroundColor(Color.CYAN)
        }
        return true
    }
}
