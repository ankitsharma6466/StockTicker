package com.example.stockticker.data.models

import com.google.gson.annotations.SerializedName

class ResponseStockIntraday: BaseResponse() {

    @SerializedName("Meta Data")
    var metaData: StockIntradayMetaDataDTO? = null

    @SerializedName("Time Series (15min)")
    var stockDetailItems: Map<String, StockIntradayItemDTO>? = null
}