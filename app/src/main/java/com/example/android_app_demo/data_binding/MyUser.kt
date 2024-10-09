package com.example.android_app_demo.data_binding

import android.graphics.drawable.Drawable
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.Date
import java.util.Locale

data class MyUser(
    val name: String,
    val lastName: String,
    val img: Drawable,
    val birthDate: Date
) {
    fun calculateBirthDate(view: View, birthDate: Date) {
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(birthDate)
        if (date != null) {
            val years = getAge(date.year, date.month, date.day)
            Snackbar.make(view, "you are currently $years old", Snackbar.LENGTH_LONG).show()
        }
    }

    fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).years
    }
}
