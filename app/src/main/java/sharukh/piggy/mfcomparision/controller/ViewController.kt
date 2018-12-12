package sharukh.piggy.mfcomparision.controller

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.widget.TextView
import sharukh.piggy.mfcomparision.R
import sharukh.piggy.mfcomparision.model.network.MFDetails


object ViewController {

    fun paintRisk(textView: TextView, mutualFund: MFDetails.MutualFund, context: Context) {

        when {

            mutualFund.details.riskometer.contains("moderate") -> {
                textView.setTextColor(context.resources.getColor(R.color.ongoing_light))
            }
            mutualFund.details.riskometer.contains("low") -> {
                textView.setTextColor(context.resources.getColor(R.color.success_light))
            }
            mutualFund.details.riskometer.contains("high") -> {
                textView.setTextColor(context.resources.getColor(R.color.failure_light))
            }
        }

        textView.text = mutualFund.details.riskometer

    }

    fun getRatingString(mutualFund: MFDetails.MutualFund, context: Context): String {
        return context.getString(R.string.rating_s, mutualFund.details.rating)
    }

    fun getMoneyString(value: Float, context: Context): String {
        return context.getString(R.string.money_f, value)
    }

    fun getPercentString(value: Float, context: Context): String {
        return context.getString(R.string.percentage_f, value)
    }

    fun getBoolString(value: Boolean): String {
        return if (value) "Yes" else "No"
    }


    fun makeTextViewHigher(id: Int, activity: Activity) {
        val textView = activity.findViewById(id) as TextView
        val str = SpannableStringBuilder(textView.text)
        str.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            0,
            textView.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = str
        textView.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.background_green))
        textView.setTextColor(ContextCompat.getColor(activity, R.color.success_dark))
    }

}