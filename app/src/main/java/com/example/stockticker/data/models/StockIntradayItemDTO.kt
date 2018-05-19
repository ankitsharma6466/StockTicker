package com.example.stockticker.data.models

import com.google.gson.annotations.SerializedName

class StockIntradayItemDTO {

    @SerializedName("1. open")
    var open: Float = 0F

    @SerializedName("2. high")
    var high: Float = 0F

    @SerializedName("3. low")
    var low: Float = 0F

    @SerializedName("4. close")
    var close: Float = 0F

    @SerializedName("5. volume")
    var volume: Int = 0
}