package sharukh.piggy.mfcomparision.data

data class MFDetails(
    val mutual_fund: MutualFund

) {
    data class MutualFund(
        val cams_code: String,
        val dividend_type: String,
        val dividend_type_text: String,
        val expense_ratio: Float,
        val plan_type: String,
        val nav: Float,
        val best_return: BestReturn,
        val details: Details
    ) {

        data class BestReturn(
            val todate: String,
            val fromdate: String,
            val percent_change: Float
        )

        data class Details(
            val rating: Float,
            val minimum_subscription: Float,
            val return_3yr: Float,
            val return_5yr: Float,
            val minimum_balance_maintainence: Float,
            val asset_aum: Float,
            val category: String,
            val riskometer: String,
            val objective: String,
            val exit_load: Float,
            val exit_load_text: String,
            val yoy_return: Float,
            val minimum_addition_subscription: Float,
            val suitability: String,
            val scheme_type: String,
            val is_elss: Boolean,
            val name: String,
            val scheme_class: String,
            val sid_url: String,
            val minimum_sip_subscription: Float
        )
    }
}