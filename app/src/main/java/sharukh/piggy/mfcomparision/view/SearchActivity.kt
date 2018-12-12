package sharukh.piggy.mfcomparision.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.search_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sharukh.piggy.mfcomparision.R
import sharukh.piggy.mfcomparision.model.network.SearchResult
import sharukh.piggy.mfcomparision.model.network.Api
import sharukh.piggy.mfcomparision.model.network.parents.GenericResponse
import sharukh.piggy.mfcomparision.controller.NetworkController

class SearchActivity : AppCompatActivity() {


    var searchResults = ArrayList<SearchResult.SearchResultMF>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initViews()
    }

    private fun initViews() {
        search_mf.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                performSearch(query)
                return true
            }
        })


        search_result_recycler.adapter = SearchResultAdapter(searchResults, this)


    }


    private fun performSearch(query: String?) {


        if (!query.isNullOrBlank()) {

            var json = JsonObject()
            json.addProperty("search", query)
            json.addProperty("rows", 7)
            json.addProperty("offset", 1)

            NetworkController.getInstance()
                .getClient(Api::class.java, this)
                .performSearch(json)
                .enqueue(object : Callback<GenericResponse<SearchResult>> {
                    override fun onFailure(call: Call<GenericResponse<SearchResult>>, t: Throwable) {
                        Snackbar.make(search_mf, "Network Error", Snackbar.LENGTH_SHORT).show()
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<GenericResponse<SearchResult>>,
                        response: Response<GenericResponse<SearchResult>>
                    ) {
                        if (response.isSuccessful) {
                            paintViews(response.body()?.data!!.search_results)
                        } else {
                            Snackbar.make(search_mf, "Response Unsuccessful", Snackbar.LENGTH_SHORT).show()
                        }
                    }

                })
        }
    }


    /*Paints the views*/
    fun paintViews(search_results: ArrayList<SearchResult.SearchResultMF>) {
        search_result_recycler.adapter = SearchResultAdapter(search_results, this)
    }


    /*Inner Classes*/

    inner class SearchResultAdapter(
        searchResults: ArrayList<SearchResult.SearchResultMF>,
        val searchActivity: SearchActivity
    ) :
        RecyclerView.Adapter<SearchResultAdapter.Holder>() {

        private var searchResults = ArrayList<SearchResult.SearchResultMF>()

        init {
            this.searchResults = searchResults
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): Holder {
            return Holder(LayoutInflater.from(viewGroup.context).inflate(R.layout.search_item, viewGroup, false))
        }

        override fun getItemCount(): Int {
            return searchResults.size
        }

        override fun onBindViewHolder(holder: Holder, pos: Int) {
            Log.w("TAG", "Binding ${searchResults.get(pos).name}")
            holder.bind(searchResults.get(pos))
        }


        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(mf: SearchResult.SearchResultMF) {
                itemView.name.text = mf.name
                itemView.yoy.text = "%.2f%%".format(mf.yoy_return)
                itemView.tyr.text = "%.2f%%".format(mf.return_3yr)
                itemView.fyr.text = "%.2f%%".format(mf.return_5yr)
                itemView.category.text = mf.category
                itemView.risk.text = mf.riskometer
                when {

                    mf.riskometer.toLowerCase().contains("high") -> {
                        itemView.risk.setTextColor(resources.getColor(R.color.failure_light))
                    }


                    mf.riskometer.toLowerCase().contains("low") -> {
                        itemView.risk.setTextColor(resources.getColor(R.color.success_light))
                    }

                    mf.riskometer.toLowerCase().contains("moderate") -> {
                        itemView.risk.setTextColor(resources.getColor(R.color.ongoing_light))
                    }


                }
                itemView.min_inv.text = "₹%.0f".format(mf.minimum_investment)
                itemView.setOnClickListener {
                    val someIntent = Intent()
                    someIntent.putExtra("MF_ID", mf.details_id)
                    searchActivity.setResult(909, someIntent)
                    searchActivity.finish()
                }
            }
        }
    }

}
