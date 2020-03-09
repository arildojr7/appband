package dev.arildo.appband.core.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import dev.arildo.appband.R

class HtmlTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatTextView(context, attrs) {

    private var notesColor: String? = ""

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.HtmlTextView, 0, 0)
        notesColor = a.getString(R.styleable.HtmlTextView_notesColor)?.removeRange(1, 3)

        a.recycle()
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        val htmlText = HtmlCompat.fromHtml(
            stylizeHtml(text.toString(), notesColor), HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        super.setText(htmlText, type)
    }

    private fun stylizeHtml(html: String?, notesColor: String?): String {
        return html?.replace(" ", "&nbsp;")
            ?.replace("\n", "<br />")
            ?.replace("<b>", "<font color='${notesColor}'><b>")
            ?.replace("</b>", "</b></font>").orEmpty()
    }
}