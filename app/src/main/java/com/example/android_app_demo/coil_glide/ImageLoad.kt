package com.example.android_app_demo.coil_glide

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.load
import coil.request.CachePolicy
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.android_app_demo.R

class ImageLoad : AppCompatActivity() {
    private lateinit var coilImageView: ImageView
    private lateinit var loadCoilImage: Button
    private lateinit var glideImageView: ImageView
    private lateinit var loadGlideImage: Button
    private lateinit var coilGifImageView: ImageView
    private lateinit var loadCoilGif: Button
    private lateinit var glideGifImageView: ImageView
    private lateinit var loadGlideGif: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_load)
        coilImageView = findViewById(R.id.coil_imageview)
        loadCoilImage = findViewById(R.id.load_coil_img)
        glideImageView = findViewById(R.id.glide_imageview)
        loadGlideImage = findViewById(R.id.load_glide_img)
        coilGifImageView = findViewById(R.id.coil_imageview_gif)
        loadCoilGif = findViewById(R.id.load_coil_gif)
        glideGifImageView = findViewById(R.id.glide_imageview_gif)
        loadGlideGif = findViewById(R.id.load_glide_gif)

        loadCoilImage.setOnClickListener {
            coilImageView.load("https://picsum.photos/300", imageLoader = ImageLoader(this)) {
                diskCachePolicy(CachePolicy.DISABLED)
                memoryCachePolicy(CachePolicy.DISABLED)
            }
        }

        loadGlideImage.setOnClickListener {
            Glide
                .with(this)
                .load("https://picsum.photos/300")
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(glideImageView)
        }

        loadCoilGif.setOnClickListener {
            val imageLoder = ImageLoader.Builder(this).components {
                add(GifDecoder.Factory())
            }.build()

            coilGifImageView.load(
                "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExYTV1eWlxZms1YzAwcWE4aDVpYXk3eGY4NWh6dWVvdmx0NG9vbmlueSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/11sBLVxNs7v6WA/giphy.gif",
                imageLoder
            )
        }

        loadGlideGif.setOnClickListener {
            Glide
                .with(this)
                .asGif()
                .load("https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExdWZvdnk0aG11aWFkMmw1NHZ2cmo0M3hocDM2bzNxN3hsczNoNWlhaSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/SbrSbKu4ocKd7o8wtt/giphy.gif")
                .fitCenter()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(glideGifImageView)
        }
    }
}