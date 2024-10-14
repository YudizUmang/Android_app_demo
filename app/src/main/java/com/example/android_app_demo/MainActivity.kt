package com.example.android_app_demo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.android_app_demo.EncryptedSharedPref.EncryptedSharedMain
import com.example.android_app_demo.activity_result.MainActivityProfile
import com.example.android_app_demo.basic_ui.basic_ui
import com.example.android_app_demo.coil_glide.ImageLoad
import com.example.android_app_demo.coordinatelayout.CoordinateMain
import com.example.android_app_demo.coroutine.Demo1
import com.example.android_app_demo.coroutine.Demo2
import com.example.android_app_demo.coroutine.Demo3
import com.example.android_app_demo.coroutine.Demo4
import com.example.android_app_demo.data_binding.DataBinding
import com.example.android_app_demo.dialogs.AddUser
import com.example.android_app_demo.fab_snackbar.FabSnackMain
import com.example.android_app_demo.fontdemo.FontMain
import com.example.android_app_demo.form.FormActivity
import com.example.android_app_demo.fragments.FragmentMain
import com.example.android_app_demo.hilt1.view.HiltActivity
import com.example.android_app_demo.image_upload.UploadImageActivity
import com.example.android_app_demo.layout.Layout
import com.example.android_app_demo.map.MapActivity
import com.example.android_app_demo.menu.MenuDemo
import com.example.android_app_demo.mvvm.view.ProductDisplay
import com.example.android_app_demo.permissions.PermissionMain
import com.example.android_app_demo.recyclerview.main.RecyclerViewMain
import com.example.android_app_demo.retrofit.UserMain
import com.example.android_app_demo.room.ToDoMain
import com.example.android_app_demo.shape_and_selector.ShapeAndSelector
import com.example.android_app_demo.shared_preferences.SharedMain
import com.example.android_app_demo.theme.ChangeTheme
import com.example.android_app_demo.viewpager_tab.recipe.RecipeMain
import com.example.android_app_demo.viewpager_tab.weather.MainWeather
import com.example.android_app_demo.webview.WebViewMain
import com.example.android_app_demo.workmanager.NetworkCharge
import com.example.android_app_demo.workmanager.Upload
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("MainActivity", "Main")

        setContentView(R.layout.activity_main)
        val basic_ui_btn = findViewById<Button>(R.id.basic_ui)
        val constraint_layout_btn = findViewById<Button>(R.id.constraint_layout)
        val form_btn = findViewById<Button>(R.id.form)
        val shape_and_selector_btn = findViewById<Button>(R.id.shape_and_selector)
        val weather_viewpager_tab = findViewById<Button>(R.id.weather)
        val recipe_viewpager_tab = findViewById<Button>(R.id.recipe)
        val recycler_view = findViewById<Button>(R.id.recycler_view)
        val coordinate_layout = findViewById<Button>(R.id.coordinate_layout)
        val fab_snackbar = findViewById<Button>(R.id.fab_snackbar)
        val fonts = findViewById<Button>(R.id.fonts_btn)
        val menu_btn = findViewById<Button>(R.id.menus)
        val date_alert_btn = findViewById<Button>(R.id.date_alert_btn)
        val activity_result_btn = findViewById<Button>(R.id.activity_result)
        val shared_pref_btn = findViewById<Button>(R.id.shared_preference)
        val fragments_btn = findViewById<Button>(R.id.fragments)
        val encrypted_shared_pref_btn = findViewById<Button>(R.id.encrypted_shared_preference)
        val permission_btn = findViewById<Button>(R.id.permission)
        val webView_btn = findViewById<Button>(R.id.webview)
        val coil_glide_btn = findViewById<Button>(R.id.coil_glide)
        val room_db_btn = findViewById<Button>(R.id.room)
        val retrofit_btn = findViewById<Button>(R.id.retrofit)
        val coroutine1 = findViewById<Button>(R.id.coroutine1)
        val coroutine2 = findViewById<Button>(R.id.coroutine2)
        val coroutine3 = findViewById<Button>(R.id.coroutine3)
        val coroutine4 = findViewById<Button>(R.id.coroutine4)
        val workManager1 = findViewById<Button>(R.id.workmanager_1)
        val workManager2 = findViewById<Button>(R.id.workmanager_2)
        val theme_btn = findViewById<Button>(R.id.theme_btn)
        val map_btn = findViewById<Button>(R.id.map_btn)
        val data_btn = findViewById<Button>(R.id.data_btn)
        val image_upload_retrofit = findViewById<Button>(R.id.img_upload_retrofit)
        val mvvm = findViewById<Button>(R.id.mvvm)
        val hilt1 = findViewById<Button>(R.id.hilt1)

        sharedPref = applicationContext.getSharedPreferences("MyTheme", 0)

        if (sharedPref.contains("UI_MODE")) {
            when (sharedPref.getString("UI_MODE", "")) {
                "UI_MODE_LIGHT" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "UI_MODE_DARK" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->

                if (!task.isSuccessful) {
                    Log.d(
                        "FCM",
                        "Fetching FCM registration token failed",
                        task.exception
                    )
                    return@addOnCompleteListener
                }
                Log.d("Token", task.result.toString())
            }

        map_btn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MapActivity::class.java
                )
            )
        }

        basic_ui_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    basic_ui::class.java
                )
            )
        }

        constraint_layout_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    Layout::class.java
                )
            )
        }

        form_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    FormActivity::class.java
                )
            )
        }

        shape_and_selector_btn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    ShapeAndSelector::class.java
                )
            )
        }

        weather_viewpager_tab.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MainWeather::class.java
                )
            )
        }

        recipe_viewpager_tab.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RecipeMain::class.java
                )
            )
        }

        recycler_view.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RecyclerViewMain::class.java
                )
            )
        }

        coordinate_layout.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    CoordinateMain::class.java
                )
            )
        }

        fab_snackbar.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    FabSnackMain::class.java
                )
            )
        }

        fonts.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    FontMain::class.java
                )
            )
        }

        menu_btn.setOnClickListener {
            startActivity(
                Intent(this, MenuDemo::class.java)
            )
        }

        date_alert_btn.setOnClickListener {
            startActivity(
                Intent(this, AddUser::class.java)
            )
        }

        activity_result_btn.setOnClickListener {
            startActivity(
                Intent(this, MainActivityProfile::class.java)
            )
        }

        shared_pref_btn.setOnClickListener {
            startActivity(
                Intent(this, SharedMain::class.java)
            )
        }

        encrypted_shared_pref_btn.setOnClickListener {
            startActivity(
                Intent(this, EncryptedSharedMain::class.java)
            )
        }

        fragments_btn.setOnClickListener {
            startActivity(
                Intent(this, FragmentMain::class.java)
            )
        }

        permission_btn.setOnClickListener {
            startActivity(Intent(this, PermissionMain::class.java))
        }

        webView_btn.setOnClickListener {
            startActivity(
                Intent(this, WebViewMain::class.java)
            )
        }

        coil_glide_btn.setOnClickListener {
            startActivity(
                Intent(this, ImageLoad::class.java)
            )
        }

        room_db_btn.setOnClickListener {
            startActivity(
                Intent(this, ToDoMain::class.java)
            )
        }

        retrofit_btn.setOnClickListener {
            startActivity(
                Intent(this, UserMain::class.java)
            )
        }

        coroutine1.setOnClickListener {
            startActivity(Intent(this, Demo1::class.java))
        }

        coroutine2.setOnClickListener {
            startActivity(Intent(this, Demo2::class.java))
        }

        coroutine3.setOnClickListener {
            startActivity(Intent(this, Demo3::class.java))
        }

        coroutine4.setOnClickListener {
            startActivity(Intent(this, Demo4::class.java))
        }

        workManager1.setOnClickListener {
            startActivity(Intent(this, Upload::class.java))
        }

        workManager2.setOnClickListener {
            startActivity(Intent(this, NetworkCharge::class.java))
        }

        theme_btn.setOnClickListener {
            startActivity(Intent(this, ChangeTheme::class.java))
        }

        data_btn.setOnClickListener {
            startActivity(Intent(this, DataBinding::class.java))
        }

        image_upload_retrofit.setOnClickListener {
            startActivity(Intent(this, UploadImageActivity::class.java))
        }

        mvvm.setOnClickListener {
            startActivity(Intent(this, ProductDisplay::class.java))
        }

        hilt1.setOnClickListener {
            startActivity(Intent(this, HiltActivity::class.java))
        }
    }
}
