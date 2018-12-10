package sharukh.piggy.mfcomparision.extensions

import android.content.Context
import android.widget.TextView
import sharukh.piggy.mfcomparision.R
import android.text.Spannable
import android.icu.lang.UProperty.INT_START
import android.support.v4.content.ContextCompat
import android.text.SpannableStringBuilder


object Extensionz {

    fun paintRisk(textView: TextView, risk: String, context: Context) {

        when {

            risk.contains("moderate") -> {
                textView.setTextColor(context.resources.getColor(R.color.ongoing_light))
            }
            risk.contains("low") -> {
                textView.setTextColor(context.resources.getColor(R.color.success_light))
            }
            risk.contains("high") -> {
                textView.setTextColor(context.resources.getColor(R.color.failure_light))
            }
        }

    }

    fun <T, U, R> Pair<T?, U?>.biLet(body: (T, U) -> R): R? {
        val first = first
        val second = second
        if (first != null && second != null) {
            return body(first, second)
        }
        return null
    }

    fun makeTextViewHigher(textView: TextView, context: Context) {
        val str = SpannableStringBuilder(textView.text)
        str.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            0,
            textView.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = str
        textView.setTextColor(ContextCompat.getColor(context, R.color.success_dark))
    }

}