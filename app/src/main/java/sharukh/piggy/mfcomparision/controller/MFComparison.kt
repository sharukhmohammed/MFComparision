package sharukh.piggy.mfcomparision.controller

import sharukh.piggy.mfcomparision.R.id.*
import sharukh.piggy.mfcomparision.model.network.MFDetails

object MFComparison {

    fun arePlanTypesSame(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Boolean {
        return mutualFund1.plan_type == mutualFund2.plan_type && mutualFund1.dividend_type == mutualFund2.dividend_type
    }

    fun ofNav(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.nav > mutualFund2.nav) first_nav else second_nav
    }

    fun ofRating(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.rating > mutualFund2.details.rating) first_rating else second_rating
    }

    fun ofMinSubscription(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.minimum_subscription < mutualFund2.details.minimum_subscription) first_min_sub else second_min_sub
    }


    fun ofAdditionalSubscription(
        mutualFund1: MFDetails.MutualFund,
        mutualFund2: MFDetails.MutualFund
    ): Int {
        return if (mutualFund1.details.minimum_addition_subscription < mutualFund2.details.minimum_addition_subscription) first_min_additional else second_min_additional
    }


    fun ofMinBalance(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.minimum_balance_maintainence < mutualFund2.details.minimum_balance_maintainence) first_min_balance else second_min_balance
    }


    fun ofMinSip(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.minimum_sip_subscription < mutualFund2.details.minimum_sip_subscription) first_min_sip else second_min_sip
    }


    fun ofExitLoad(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.exit_load < mutualFund2.details.exit_load) first_exit_load else second_exit_load
    }


    fun ofReturnYoy(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.yoy_return > mutualFund2.details.yoy_return) first_return_yoy else second_return_yoy
    }


    fun ofReturn3Yr(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.return_3yr > mutualFund2.details.return_3yr) first_return_3yr else second_return_3yr
    }


    fun ofReturn5Yr(mutualFund1: MFDetails.MutualFund, mutualFund2: MFDetails.MutualFund): Int {
        return if (mutualFund1.details.return_5yr > mutualFund2.details.return_5yr) first_return_5yr else second_return_5yr
    }


}