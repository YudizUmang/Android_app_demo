package com.example.android_app_demo.webview

import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class WebViewMain : AppCompatActivity() {
    private lateinit var webViewUrl: WebView
    private lateinit var webViewHtml: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view_main)
        webViewHtml = findViewById(R.id.webviewhtml)
        webViewUrl = findViewById(R.id.webviewurl)
        val unencodedHtml =
            "<html><body> Hello, Umang here!!! </body></html>"
        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
        webViewHtml.loadData(encodedHtml, "text/html", "base64")

        webViewUrl.loadUrl("https://verygood.ventures/")
        webViewUrl.webViewClient = WebViewClient()
        webViewUrl.settings.javaScriptEnabled = true
    }
}