package com.example.stockticker.common

import com.example.stockticker.data.models.MarketTimings
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

object StockMarketHelper {

    /**
     * returns the market timings - start and close as 'MarketTimings'
     */
    fun getMarketTimings(date: Int): MarketTimings {

        var marketTimings = MarketTimings()

        var calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone(Constants.EST_TIMEZONE)
        calendar.set(Calendar.DATE, date)
        calendar.add(Calendar.DATE, -1)
        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 30)
        calendar.set(Calendar.SECOND, 0)

        marketTimings.openTime = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, 16)
        calendar.set(Calendar.MINUTE, 0)

        marketTimings.closeTime = calendar.timeInMillis

        return marketTimings
    }

    fun getLastUpdatedTimeStr(millis: Long): String {
        val format = SimpleDateFormat("dd MMM, HH:mm", Locale.ENGLISH)

        val cal: Calendar = Calendar.getInstance()
        cal.timeInMillis = millis

        val str = format.format(cal.time)

        Timber.d("time >>>>>>>>>>>>>>>>>>>>>>>>>>> $str")

        return "Last Updated $str"
    }
}