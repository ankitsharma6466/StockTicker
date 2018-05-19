package com.example.stockticker.common

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("Error Message")
    var errorMessage: String? = null

    @SerializedName("Information")
    var infoMessage: String? = null
}