package com.example.stockticker

import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.models.StockIntradayItemDTO

object DataParsingUtils {

    fun getDeducedInfo(stockDetailItems: Map<String, StockIntradayItemDTO>): DeducedStockDetailsDTO {

        var deducedStockDetailsDTO = DeducedStockDetailsDTO()

//        for((key, value) in stockDetailItems) {
//
//            deducedStockDetailsDTO.current =
//
//
//
//        }

        return deducedStockDetailsDTO
    }
}