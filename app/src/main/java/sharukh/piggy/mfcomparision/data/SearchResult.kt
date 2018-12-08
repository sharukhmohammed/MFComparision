package sharukh.piggy.mfcomparision.data

data class SearchResult(
    val search_query: String,
    val search_results_count: Int,
    val search_results: ArrayList<SearchResultMF>
) {
    data class SearchResultMF(
        val category: String,
        val rating: Float,
        val minimum_investment: Float,
        val sub_category: String,
        val yoy_return: Float,
        val return_3yr: Float,
        val return_5yr: Float,
        val riskometer: String,
        val details_id: String,
        val id: Long,
        val name: String
    )
}