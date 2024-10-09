package com.example.android_app_demo.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android_app_demo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentMain : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragments_main)


        loadFragment(HomeFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.btmNavBar)!!
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.imHome -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.imSearch -> {
                    loadFragment(SearchFragment())
                    true
                }

                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

}