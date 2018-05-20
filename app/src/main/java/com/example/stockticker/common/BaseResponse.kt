package com.example.stockticker.common

import com.google.gson.annotations.SerializedName

/**
 * Created by ankitsharma on 19/05/18.
 */
open class BaseResponse {

    @SerializedName("Error Message")
    var errorMessage: String? = null

    @SerializedName("Information")
    var infoMessage: String? = null
}