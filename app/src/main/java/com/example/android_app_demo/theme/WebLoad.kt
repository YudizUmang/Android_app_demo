package com.example.android_app_demo.theme

import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.example.android_app_demo.R

class WebLoad : AppCompatActivity() {
    private lateinit var webView: WebView

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_load)
        webView = findViewById(R.id.load_web)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://developer.android.com/develop/ui/views/layout/webapps/dark-theme")
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            //WebSettingsCompat.setForceDark(webView.settings, WebSettingsCompat.FORCE_DARK_ON)
            WebSettingsCompat.setAlgorithmicDarkeningAllowed(webView.settings, true)
        }
    }
}