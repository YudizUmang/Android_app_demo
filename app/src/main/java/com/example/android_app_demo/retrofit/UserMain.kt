package com.example.android_app_demo.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app_demo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserMain : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var getUserBtn: Button
    private lateinit var addUserBtn: Button
    private lateinit var progressBar: ProgressBar
    var userList: MutableList<User> = mutableListOf()
    private var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_main)
        recyclerView = findViewById(R.id.display_user_list)
        getUserBtn = findViewById(R.id.get_user_btn)
        addUserBtn = findViewById(R.id.add_user_btn)
        progressBar = findViewById(R.id.progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this, userList)
        recyclerView.adapter = userAdapter

        getUserBtn.setOnClickListener {
            getUser()
        }

        addUserBtn.setOnClickListener {
            showDialog(isEdit, null, null)
        }

        userAdapter.onEditClick { position: Int, user: User ->
            isEdit = true
            showDialog(isEdit, position, user)
        }

        userAdapter.onDeleteClick { position: Int, user: User ->
            deleteUser(user, position)
        }
        /* userAdapter.onDeleteClick(object : UserClickListener {
             override fun setUserListener(view: View, position: Int, user: User) {
                 deleteUser(user)
             }

         })*/

    }

    private fun showDialog(isEdit: Boolean, position: Int?, user: User?) {
        val dialogView =
            layoutInflater.inflate(R.layout.add_user_dialog, null)
        val nameInput = dialogView.findViewById<EditText>(R.id.name_edit_text)
        val companyInput = dialogView.findViewById<EditText>(R.id.company_name_edit_text)
        val ageInput = dialogView.findViewById<EditText>(R.id.user_age_edit_text)
        val imgInput = dialogView.findViewById<EditText>(R.id.img_url_edit_text)
        val addUserBtn = dialogView.findViewById<Button>(R.id.submit_button)
        if (isEdit) {
            nameInput.setText(user!!.name)
            companyInput.setText(user.companyName)
            ageInput.setText(user.age.toString())
            imgInput.setText(user.img)
            val dialogBuilder = AlertDialog.Builder(this).apply {
                setTitle("Edit User")
                setView(dialogView)
                setCancelable(true)
            }
            val alertDialog = dialogBuilder.create()
            alertDialog.show()

            addUserBtn.setOnClickListener {

                editUser(
                    nameInput.text.toString(),
                    companyInput.text.toString(),
                    ageInput.text.toString().toInt(),
                    imgInput.text.toString(),
                    user.userId,
                    position
                )
                alertDialog.dismiss()
            }
        } else {
            val dialogBuilder = AlertDialog.Builder(this).apply {
                setTitle("Add User")
                setView(dialogView)
                setCancelable(true)
            }
            val alertDialog = dialogBuilder.create()
            alertDialog.show()
            addUserBtn.setOnClickListener {
                addUser(
                    nameInput.text.toString(),
                    companyInput.text.toString(),
                    ageInput.text.toString().toInt(),
                    imgInput.text.toString()
                )
                alertDialog.dismiss()
            }
        }

    }

    private fun addUser(nameInput: String, companyInput: String, ageInput: Int, imgInput: String) {
        progressBar.visibility = View.VISIBLE
        RetrofitInstance.apiInterface.addUser(
            User(
                nameInput,
                companyInput,
                imgInput,
                ageInput,
                ""
            )
        )
            .enqueue(object : Callback<User?> {

                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    Log.d("added", response.body().toString())
                    progressBar.visibility = View.GONE


                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    Log.d("err", t.toString())
                    progressBar.visibility = View.GONE
                }

            })
    }

    private fun getUser() {
        progressBar.visibility = View.VISIBLE
        RetrofitInstance.apiInterface.getUser().enqueue(object : Callback<List<User>?> {
            override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                userList = (response.body() ?: mutableListOf()).toMutableList()
                userAdapter.updateUserList(userList)
                Log.d("res", response.body().toString())
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                Log.d("err", t.toString())
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun editUser(
        nameInput: String,
        companyInput: String,
        ageInput: Int,
        imgInput: String,
        id: String,
        position: Int?
    ) {
        progressBar.visibility = View.VISIBLE
        RetrofitInstance.apiInterface.editUser(
            id,
            User(nameInput, companyInput, imgInput, ageInput, id)
        )
            .enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    Log.d("edited", response.body().toString())
                    with(userList[position!!]) {
                        name = nameInput
                        companyName = companyInput
                        age = ageInput
                        img = imgInput
                    }
                    userAdapter.updateUserList(userList)
                    progressBar.visibility = View.GONE

                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    Log.d("err", t.toString())
                    progressBar.visibility = View.GONE
                }

            })
    }

    private fun deleteUser(
        user: User,
        position: Int
    ) {
        progressBar.visibility = View.VISIBLE
        RetrofitInstance.apiInterface.deleteUser(
            user.userId
        )
            .enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>, response: Response<User?>) {
                    Log.d("deleted", response.body().toString())
                    userList.removeAt(position)
                    userAdapter.updateUserList(userList)
                    progressBar.visibility = View.GONE
                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    Log.d("err", t.toString())
                    progressBar.visibility = View.GONE
                }

            })
    }
}