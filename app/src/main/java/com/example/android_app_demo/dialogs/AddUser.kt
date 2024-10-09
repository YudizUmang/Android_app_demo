package com.example.android_app_demo.dialogs

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R
import java.util.Calendar


class AddUser : AppCompatActivity() {
    private lateinit var userDetailsdob: TextView
    private lateinit var userDetailsName: TextView
    private lateinit var userDetailsLastName: TextView
    private lateinit var addUserButton: Button
    private lateinit var dateOfBirthEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_user)
        userDetailsdob = findViewById(R.id.user_details_dob)
        userDetailsName = findViewById(R.id.user_details_name)
        userDetailsLastName = findViewById(R.id.user_details_last_name)
        addUserButton = findViewById(R.id.add_user_btn)

        addUserButton.setOnClickListener {
            showAddUserDialog()
        }

    }

    private fun showAddUserDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_view, null)
        dateOfBirthEditText = dialogView.findViewById(R.id.date_of_birth_edit_text)
        val submitButton: Button = dialogView.findViewById(R.id.submit_button)
        val nameEditText: EditText = dialogView.findViewById(R.id.name_edit_text)
        val lastNameEditText: EditText = dialogView.findViewById(R.id.last_nm_edit_text)

        val dialogBuilder = AlertDialog.Builder(this).apply {
            setTitle("Add User")
            setView(dialogView)
            setCancelable(true)
        }

        val alertDialog = dialogBuilder.create()

        dateOfBirthEditText.setOnClickListener {
            showDatePicker()
        }

        submitButton.setOnClickListener {
            val dateOfBirth = dateOfBirthEditText.text.toString()
            userDetailsdob.text = "Date of Birth: $dateOfBirth"
            userDetailsName.text = nameEditText.text
            userDetailsLastName.text = lastNameEditText.text
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                dateOfBirthEditText.setText(date)
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()
    }
}