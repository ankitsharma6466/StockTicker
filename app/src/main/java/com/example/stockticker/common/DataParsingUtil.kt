package com.example.stockticker.common

import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.models.MarketTimings
import com.example.stockticker.data.models.StockIntradayItemDTO
import com.github.mikephil.charting.data.Entry
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

/**
 * Created by ankitsharma on 19/05/18.
 */
object DataParsingUtil {

    /**
     * Converts the stock market list data to cumulative information
     */
    fun getDeducedInfo(stockDetailItems: Map<String, StockIntradayItemDTO>): DeducedStockDetailsDTO {

        val deducedStockDetailsDTO = DeducedStockDetailsDTO()
        var isFirst = true
        var highest = 0F
        var lowest = 0F
        val entryList: ArrayList<Float> = ArrayList()
        lateinit var marketTimings: MarketTimings


        for((key, value) in stockDetailItems) {

            val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            dateFormatter.timeZone = TimeZone.getTimeZone(Constants.EST_TIMEZONE)

            //we have date
            val date = dateFormatter.parse(key)
            val calendar = Calendar.getInstance()
            calendar.time = date

            //this gets us the current value from first object
            //fetch latest/current value and volume for the stock
            if(isFirst){
                deducedStockDetailsDTO.current = "${value.open}"
                deducedStockDetailsDTO.volume = "${value.volume}"
                deducedStockDetailsDTO.lowest = "${value.low}"
                deducedStockDetailsDTO.date = calendar.timeInMillis
                marketTimings = StockMarketHelper.getMarketTimings(calendar.get(Calendar.DATE))
                lowest = value.low
                isFirst = false
            }


            if(highest < value.high) highest = value.high

            if(lowest > value.low) lowest = value.low

            //prepare graph data
            entryList.add(value.open)

            deducedStockDetailsDTO.open = "${value.open}"

            if(calendar.timeInMillis < marketTimings.openTime) {
                //break the loop here as we reached last value for the day
                break
            }
        }

        deducedStockDetailsDTO.highest = "$highest"
        deducedStockDetailsDTO.lowest = "$lowest"
        deducedStockDetailsDTO.graphicalInfo = getFormattedChartData(entryList)

        return deducedStockDetailsDTO
    }

    /**
     * Converts the price list into required chart format
     */
    private fun getFormattedChartData(entries: List<Float>): List<Entry> {

        val finalData: ArrayList<Entry> = ArrayList()

        var index = entries.size-1
        for(data in entries) {
            finalData.add(Entry(index.toFloat(), data))
            index--
        }

        return finalData
    }
}