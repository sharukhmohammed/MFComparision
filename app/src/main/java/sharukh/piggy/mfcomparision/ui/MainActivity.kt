package sharukh.piggy.mfcomparision.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sharukh.piggy.mfcomparision.R
import sharukh.piggy.mfcomparision.data.MFDetails
import sharukh.piggy.mfcomparision.extensions.Extensionz
import sharukh.piggy.mfcomparision.network.Api
import sharukh.piggy.mfcomparision.network.GenericResponse
import sharukh.piggy.mfcomparision.network.RetrofitClient
import java.security.cert.Extension

class MainActivity : AppCompatActivity() {

    var firstMf: MFDetails.MutualFund? = null
    var secondMf: MFDetails.MutualFund? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        first_title.setOnClickListener {
            intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, 1024)
        }


        second_title.setOnClickListener {
            intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, 2048)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {
            //SearchActivity for First MF
            1024 -> {
                RetrofitClient.getInstance().getClient(Api::class.java, this)
                    .getMFDetails(data?.getStringExtra("MF_ID").toString())
                    .enqueue(object : Callback<GenericResponse<MFDetails>> {
                        override fun onFailure(call: Call<GenericResponse<MFDetails>>, t: Throwable) {
                            Snackbar.make(first_title, "Network Error", Snackbar.LENGTH_SHORT).show()
                            t.printStackTrace()
                        }

                        override fun onResponse(
                            call: Call<GenericResponse<MFDetails>>,
                            response: Response<GenericResponse<MFDetails>>
                        ) {
                            if (response.isSuccessful) {
                                firstMf = response.body()?.data?.mutual_fund!!
                                paintViews()
                            } else
                                Snackbar.make(second_title, "Response Unsuccessful", Snackbar.LENGTH_SHORT).show()

                        }

                    })
            }

            //SearchActivity for Second MF
            2048 -> {
                RetrofitClient.getInstance().getClient(Api::class.java, this)
                    .getMFDetails(data?.getStringExtra("MF_ID").toString())
                    .enqueue(object : Callback<GenericResponse<MFDetails>> {
                        override fun onFailure(call: Call<GenericResponse<MFDetails>>, t: Throwable) {
                            Snackbar.make(first_title, "Network Error", Snackbar.LENGTH_SHORT).show()
                            t.printStackTrace()
                        }

                        override fun onResponse(
                            call: Call<GenericResponse<MFDetails>>,
                            response: Response<GenericResponse<MFDetails>>
                        ) {
                            if (response.isSuccessful) {
                                secondMf = response.body()?.data?.mutual_fund!!
                                paintViews()
                            } else
                                Snackbar.make(second_title, "Response Unsuccessful", Snackbar.LENGTH_SHORT).show()

                        }

                    })
            }
        }

        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun paintViews() {
        firstMf?.let {
            first_title.text = it.details.name
            first_catergory.text = it.plan_type
            first_rating.text = "%.2f ★".format(it.details.rating)
            first_nav.text = "₹ %.2f".format(it.nav)
            first_min_sub.text = "₹ %.2f".format(it.details.minimum_subscription)
            first_riskometer.text = it.details.riskometer
            Extensionz.paintRisk(first_riskometer,it.details.riskometer,this)
            first_return_yoy.text = "%.0f%%".format(it.details.yoy_return)
            first_return_3yr.text = "%.0f%%".format(it.details.return_3yr)
            first_return_5yr.text = "%.0f%%".format(it.details.return_5yr)
            first_scheme_class.text = it.details.scheme_class
            first_scheme_type.text = it.details.scheme_type
            first_scheme_type.text = it.details.scheme_type
            first_is_elss.text = if (it.details.is_elss) "Yes" else "No"
            first_min_sip.text = "₹ %.2f".format(it.details.minimum_sip_subscription)
            first_min_balance.text = "₹ %.2f".format(it.details.minimum_balance_maintainence)
            first_min_additional.text = "₹ %.2f".format(it.details.minimum_addition_subscription)
            first_exit_load.text = it.details.exit_load_text
            first_objective.text = it.details.objective
            first_suitability.text = it.details.suitability
            first_download_sid.text = if (it.details.sid_url.isBlank()) "Yes" else "No"
        }


        secondMf?.let {
            second_title.text = it.details.name
            second_catergory.text = it.plan_type
            second_rating.text = "%.2f  ★".format(it.details.rating)
            second_nav.text = "₹ %.2f".format(it.nav)
            second_min_sub.text = "₹ %.2f".format(it.details.minimum_subscription)
            second_riskometer.text = it.details.riskometer
            Extensionz.paintRisk(second_riskometer,it.details.riskometer,this)
            second_return_yoy.text = "%.0f%%".format(it.details.yoy_return)
            second_return_3yr.text = "%.0f%%".format(it.details.return_3yr)
            second_return_5yr.text = "%.0f%%".format(it.details.return_5yr)
            second_scheme_class.text = it.details.scheme_class
            second_scheme_type.text = it.details.scheme_type
            second_scheme_type.text = it.details.scheme_type
            second_is_elss.text = if (it.details.is_elss) "Yes" else "No"
            second_min_sip.text = "₹ %.2f".format(it.details.minimum_sip_subscription)
            second_min_balance.text = "₹ %.2f".format(it.details.minimum_balance_maintainence)
            second_min_additional.text = "₹ %.2f".format(it.details.minimum_addition_subscription)
            second_exit_load.text = it.details.exit_load_text
            second_objective.text = it.details.objective
            second_suitability.text = it.details.suitability
            second_download_sid.text = if (it.details.sid_url.isBlank()) "Yes" else "No"
        }


        //Start Comparision and Paint effectively
        if (firstMf != null && secondMf != null) {


            if (!firstMf!!.plan_type.equals(secondMf!!.plan_type) || !firstMf!!.dividend_type.equals(secondMf!!.dividend_type)) {
                Snackbar.make(
                    first_title,
                    "Different plan types might not be appropriate to compare",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            Extensionz.makeTextViewHigher(if (firstMf!!.nav > secondMf!!.nav) first_nav else second_nav, this)
            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.rating > secondMf!!.details.rating) first_rating else second_rating,
                this
            )

            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.minimum_subscription < secondMf!!.details.minimum_subscription) first_min_sub else second_min_sub,
                this
            )

            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.minimum_addition_subscription < secondMf!!.details.minimum_addition_subscription) first_min_additional else second_min_additional,
                this
            )

            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.minimum_balance_maintainence < secondMf!!.details.minimum_balance_maintainence) first_min_balance else second_min_balance,
                this
            )

            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.minimum_sip_subscription < secondMf!!.details.minimum_sip_subscription) first_min_sip else second_min_sip,
                this
            )

            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.exit_load < secondMf!!.details.exit_load) first_exit_load else second_exit_load,
                this
            )

            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.yoy_return > secondMf!!.details.yoy_return) first_return_yoy else second_return_yoy,
                this
            )

            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.return_3yr > secondMf!!.details.return_3yr) first_return_3yr else second_return_3yr,
                this
            )


            Extensionz.makeTextViewHigher(
                if (firstMf!!.details.return_5yr > secondMf!!.details.return_5yr) first_return_5yr else second_return_5yr,
                this
            )


        }
    }
}
