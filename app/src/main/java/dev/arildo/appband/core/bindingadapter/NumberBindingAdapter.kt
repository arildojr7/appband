package dev.arildo.appband.core.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat

@BindingAdapter("bind:longToDate")
fun TextView.longToDate(timestamp: Long?) {
    this.text = SimpleDateFormat("dd/MM/yy").format(timestamp)
}