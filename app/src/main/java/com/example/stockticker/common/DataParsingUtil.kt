package com.example.stockticker.common

import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.models.StockIntradayItemDTO
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.LinkedHashMap

object DataParsingUtil {

    fun getDeducedInfo(stockDetailItems: Map<String, StockIntradayItemDTO>): DeducedStockDetailsDTO {

        var deducedStockDetailsDTO = DeducedStockDetailsDTO()
        var isFirst = true
        var highest = 0F
        var lowest = 0F
        var marketTimings = StockMarketHelper.getMarketTimings()
        deducedStockDetailsDTO.graphicalInfo = LinkedHashMap()

        Timber.d("open time     ${marketTimings.openTime}")
        Timber.d("close time     ${marketTimings.closeTime}")

        for((key, value) in stockDetailItems) {

            val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            dateFormatter.timeZone = TimeZone.getTimeZone("America/New_York")

            //we have date
            val date = dateFormatter.parse(key)
            val calendar = Calendar.getInstance()
            calendar.time = date

            Timber.d("current item >> $date")

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
            var key = "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
            deducedStockDetailsDTO.graphicalInfo[key] = value.open

            deducedStockDetailsDTO.open = "${value.open}"

            if(calendar.timeInMillis < marketTimings.openTime) {
                //break the loop here as we reached last value for the day
                break
            }
        }

        deducedStockDetailsDTO.highest = "$highest"
        deducedStockDetailsDTO.lowest = "$lowest"

        return deducedStockDetailsDTO
    }
}