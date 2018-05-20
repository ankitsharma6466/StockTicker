package com.example.stockticker.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by ankitsharma on 19/05/18.
 */
class StockIntradayMetaDataDTO {

    @SerializedName("1. Information")
    var information: String = ""

    @SerializedName("2. Symbol")
    var symbol: String = ""

    @SerializedName("3. Last Refreshed")
    var lastRefreshed: String = ""

    @SerializedName("4. Interval")
    var Interval: String = ""

    @SerializedName("5. Output Size")
    var outputSize: String = ""

    @SerializedName("6. Time Zone")
    var timeZone: String = ""
}