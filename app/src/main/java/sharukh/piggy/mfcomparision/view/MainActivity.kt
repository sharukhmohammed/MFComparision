package sharukh.piggy.mfcomparision.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sharukh.piggy.mfcomparision.R
import sharukh.piggy.mfcomparision.model.network.MFDetails
import sharukh.piggy.mfcomparision.controller.ViewController
import sharukh.piggy.mfcomparision.controller.MFComparison
import sharukh.piggy.mfcomparision.model.network.Api
import sharukh.piggy.mfcomparision.model.network.parents.GenericResponse
import sharukh.piggy.mfcomparision.controller.NetworkController

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
                NetworkController.getInstance().getClient(Api::class.java, this)
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
                NetworkController.getInstance().getClient(Api::class.java, this)
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

        actual_layout.visibility = View.VISIBLE

        firstMf?.let {
            first_title.text = it.details.name
            first_catergory.text = it.plan_type
            first_rating.text = ViewController.getRatingString(it, this)
            first_nav.text = ViewController.getMoneyString(it.nav, this)
            first_min_sub.text = ViewController.getMoneyString(it.details.minimum_subscription, this)
            ViewController.paintRisk(first_riskometer, it, this)
            first_return_yoy.text = ViewController.getPercentString(it.details.yoy_return, this)
            first_return_3yr.text = ViewController.getPercentString(it.details.return_3yr, this)
            first_return_5yr.text = ViewController.getPercentString(it.details.return_5yr, this)
            first_scheme_class.text = it.details.scheme_class
            first_scheme_type.text = it.details.scheme_type
            first_scheme_type.text = it.details.scheme_type
            first_is_elss.text = ViewController.getBoolString(it.details.is_elss)
            first_min_sip.text = ViewController.getMoneyString(it.details.minimum_sip_subscription, this)
            first_min_balance.text = ViewController.getMoneyString(it.details.minimum_balance_maintainence, this)
            first_min_additional.text = ViewController.getMoneyString(it.details.minimum_addition_subscription, this)
            first_exit_load.text = it.details.exit_load_text
            first_objective.text = it.details.objective
            first_suitability.text = it.details.suitability
            first_download_sid.text = ViewController.getBoolString(it.details.sid_url.isBlank())
        }


        secondMf?.let {
            second_title.text = it.details.name
            second_catergory.text = it.plan_type
            second_rating.text = ViewController.getRatingString(it, this)
            second_nav.text = ViewController.getMoneyString(it.nav, this)
            second_min_sub.text = ViewController.getMoneyString(it.details.minimum_subscription, this)
            ViewController.paintRisk(second_riskometer, it, this)
            second_return_yoy.text = ViewController.getPercentString(it.details.yoy_return, this)
            second_return_3yr.text = ViewController.getPercentString(it.details.return_3yr, this)
            second_return_5yr.text = ViewController.getPercentString(it.details.return_5yr, this)
            second_scheme_class.text = it.details.scheme_class
            second_scheme_type.text = it.details.scheme_type
            second_scheme_type.text = it.details.scheme_type
            second_is_elss.text = ViewController.getBoolString(it.details.is_elss)
            second_min_sip.text = ViewController.getMoneyString(it.details.minimum_sip_subscription, this)
            second_min_balance.text = ViewController.getMoneyString(it.details.minimum_balance_maintainence, this)
            second_min_additional.text = ViewController.getMoneyString(it.details.minimum_addition_subscription, this)
            second_exit_load.text = it.details.exit_load_text
            second_objective.text = it.details.objective
            second_suitability.text = it.details.suitability
            second_download_sid.text = ViewController.getBoolString(it.details.sid_url.isBlank())
        }

        //Start Comparision and Paint effectively
        if (firstMf != null && secondMf != null) {


            if (!MFComparison.arePlanTypesSame(firstMf!!, secondMf!!)) {
                Snackbar.make(
                    first_title,
                    "Different plan types might not be appropriate to compare",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            ViewController.makeTextViewHigher(MFComparison.ofNav(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofRating(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofMinSubscription(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofAdditionalSubscription(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofMinBalance(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofMinSip(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofExitLoad(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofReturnYoy(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofReturn3Yr(firstMf!!, secondMf!!), this)
            ViewController.makeTextViewHigher(MFComparison.ofReturn5Yr(firstMf!!, secondMf!!), this)

        }
    }
}
