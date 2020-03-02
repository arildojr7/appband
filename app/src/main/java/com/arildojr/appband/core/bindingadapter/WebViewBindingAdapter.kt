package com.arildojr.appband.core.bindingadapter

import android.util.Base64
import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:parseHtml")
fun WebView.parseHtml(html: String?) {
    html?.let {
        val encodedHtml = Base64.encodeToString(html.toByteArray(), Base64.NO_PADDING)
        this.loadData(encodedHtml, "text/html", "base64")
    }
}