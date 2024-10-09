package com.example.android_app_demo.fab_snackbar

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_app_demo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FabSnackMain : AppCompatActivity() {
    private lateinit var mainFab: FloatingActionButton
    private lateinit var saveFab: FloatingActionButton
    private lateinit var shareFab: FloatingActionButton
    private var isExpanded = false

    private val fromBottomFabAnim: Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(this, R.anim.from_fab)
    }

    private val toBottomFabAnim: Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(this, R.anim.to_fab)
    }

    private val rotateClockwiseFab: Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
    }

    private val rotateAntiClockwiseFab: Animation by lazy {
        android.view.animation.AnimationUtils.loadAnimation(this, R.anim.rotate_anti_clockwise)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fab)
        val main = findViewById<ConstraintLayout>(R.id.constraint)


        mainFab = findViewById(R.id.main_fab)
        saveFab = findViewById(R.id.fab_save)
        shareFab = findViewById(R.id.fab_share)


        mainFab.setOnClickListener {
            if (isExpanded) {
                shrinkFab()
            } else {
                expandFab()
            }
        }

        saveFab.setOnClickListener {
            Snackbar.make(
                main, "Want to Save the File",
                Snackbar.LENGTH_LONG
            ).setAction("OK") {
                Snackbar.make(main, "File Saved", Snackbar.LENGTH_LONG).show()
            }.show()
        }

        shareFab.setOnClickListener {
            Snackbar.make(main, "This is Share button", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun shrinkFab() {
        saveFab.visibility = View.GONE
        shareFab.visibility = View.GONE
        mainFab.startAnimation(rotateAntiClockwiseFab)
        shareFab.startAnimation(toBottomFabAnim)
        saveFab.startAnimation(toBottomFabAnim)
        isExpanded = !isExpanded
    }

    private fun expandFab() {
        saveFab.visibility = View.VISIBLE
        shareFab.visibility = View.VISIBLE
        mainFab.startAnimation(rotateClockwiseFab)
        shareFab.startAnimation(fromBottomFabAnim)
        saveFab.startAnimation(fromBottomFabAnim)
        isExpanded = !isExpanded
    }
}
