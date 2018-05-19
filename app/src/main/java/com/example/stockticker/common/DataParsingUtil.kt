package com.example.stockticker.common

import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.models.StockIntradayItemDTO
import com.github.mikephil.charting.data.Entry
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

object DataParsingUtil {

    fun getDeducedInfo(stockDetailItems: Map<String, StockIntradayItemDTO>): DeducedStockDetailsDTO {

        val deducedStockDetailsDTO = DeducedStockDetailsDTO()
        var isFirst = true
        var highest = 0F
        var lowest = 0F
        val marketTimings = StockMarketHelper.getMarketTimings()
        val entryList: ArrayList<Float> = ArrayList()


        for((key, value) in stockDetailItems) {

            val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            dateFormatter.timeZone = TimeZone.getTimeZone("America/New_York")

            //we have date
            val date = dateFormatter.parse(key)
            val calendar = Calendar.getInstance()
            calendar.time = date

            //this gets us the current value from first object
            //fetch current value and volume for the stock
            if(isFirst){
                deducedStockDetailsDTO.current = "${value.open}"
                deducedStockDetailsDTO.volume = "${value.volume}"
                deducedStockDetailsDTO.lowest = "${value.low}"
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

    fun getFormattedChartData(entries: List<Float>): List<Entry> {

        var finalData: ArrayList<Entry> = ArrayList()
//        var reverseList = entries.reversed()

        var index = entries.size-1
        for(data in entries) {
            finalData.add(Entry(index.toFloat(), data))
            index--
        }

        return finalData
    }
}