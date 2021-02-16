package com.example.myapplication

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class Video : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webview = WebView(this)
        val IPVal3: String = intent.getStringExtra("IPValT2").toString()
        val IPfeed: String = "http://" + IPVal3 + "/stream"
        setContentView(webview)
        webview.loadUrl(IPfeed)
    }
}