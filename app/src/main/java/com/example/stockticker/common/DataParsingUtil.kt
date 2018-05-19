package com.example.stockticker.common

import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.models.StockIntradayItemDTO
import java.text.SimpleDateFormat
import java.util.*

object DataParsingUtil {

    fun getDeducedInfo(stockDetailItems: Map<String, StockIntradayItemDTO>): DeducedStockDetailsDTO {

        var deducedStockDetailsDTO = DeducedStockDetailsDTO()
        var isFirst = true
        var highest = 0F
        var lowest = 0F

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
                deducedStockDetailsDTO.calendar = calendar
                deducedStockDetailsDTO.volume = "${value.volume}"
                deducedStockDetailsDTO.lowest = "${value.low}"
                lowest = value.low
                isFirst = false
            }

            if(highest < value.high) highest = value.high

            if(lowest > value.low) lowest = value.low

            if(deducedStockDetailsDTO.calendar.get(Calendar.DATE) != calendar.get(Calendar.DATE)) {
                deducedStockDetailsDTO.open = "${value.open}"

                //break the loop here as we reached last value for the day
                break
            }
        }

        deducedStockDetailsDTO.highest = "$highest"
        deducedStockDetailsDTO.lowest = "$lowest"

        return deducedStockDetailsDTO
    }
}