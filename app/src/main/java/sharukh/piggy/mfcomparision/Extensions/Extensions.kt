package sharukh.piggy.mfcomparision.Extensions

import android.widget.TextView
import sharukh.piggy.mfcomparision.R


class Extensions{

    fun TextView.paintRisk(risk:String){

        when{

            risk.contains("low") -> {
                setTextColor(resources.getColor(R.color.success_light))
            }

            risk.contains("moderate") -> {
                setTextColor(resources.getColor(R.color.ongoing_light))
            }

            risk.contains("high") -> {
                setTextColor(resources.getColor(R.color.failure_light))
            }
        }

    }

}