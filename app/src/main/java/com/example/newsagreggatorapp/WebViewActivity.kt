package com.example.newsagreggatorapp

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView

/**
 * This class represent web view activity. It is resposibile to open article url in aplication.
 *
 * @author Piotr Obara
 *967793
 */
class WebViewActivity: Activity() {

    var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_activity)

        var extras: Bundle? = getIntent().extras

        if (extras != null) {
            url = extras.getString("url")

            var webView = findViewById<WebView>(R.id.webView)
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url!!)

        }
    }

}