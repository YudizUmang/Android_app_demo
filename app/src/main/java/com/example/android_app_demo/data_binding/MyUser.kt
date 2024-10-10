package com.example.android_app_demo.data_binding

import android.graphics.drawable.Drawable
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.Period

data class MyUser(
    val name: String,
    val lastName: String,
    val img: Drawable,
    val birthDate: LocalDate
) {
    fun calculateAge(view: View) {
        val currentDate = LocalDate.now()
        val age = Period.between(birthDate, currentDate).years
        Snackbar.make(view, "You are currently $age years old", Snackbar.LENGTH_LONG).show()
    }
}
