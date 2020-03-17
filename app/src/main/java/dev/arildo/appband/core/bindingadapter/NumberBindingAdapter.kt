package dev.arildo.appband.core.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bind:longToDate")
fun TextView.longToDate(timestamp: Long?) {
    this.text = SimpleDateFormat("EEE dd/MM/yy", Locale("pt", "BR")).format(timestamp)
}