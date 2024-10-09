package com.example.android_app_demo.coordinatelayout

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.android_app_demo.R

class CoordinateMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coordinate_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        Log.d("CoordinateMain1", "Menu created")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.info -> {
                Toast.makeText(this, "Info Selected", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.create_new -> {
                Toast.makeText(this, "New Selected", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.save -> {
                Toast.makeText(this, "Save Selected", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
