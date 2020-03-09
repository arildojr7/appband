package dev.arildo.appband.core.util

import android.util.DisplayMetrics
import android.util.TypedValue
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

fun Int.convertDpToPx(displayMetrics: DisplayMetrics): Int {
    val pixels =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            displayMetrics
        )
    return pixels.roundToInt()
}