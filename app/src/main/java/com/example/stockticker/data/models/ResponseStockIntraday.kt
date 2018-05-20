package com.example.stockticker.data.models

import com.example.stockticker.common.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by ankitsharma on 19/05/18.
 */
class ResponseStockIntraday: BaseResponse() {

    @SerializedName("Meta Data")
    var metaData: StockIntradayMetaDataDTO? = null

    @SerializedName("Time Series (5min)")
    var stockDetailItems: Map<String, StockIntradayItemDTO>? = null
}