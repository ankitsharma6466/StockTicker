package com.example.stockticker.data.models

import com.example.stockticker.common.Constants
import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("Error Message")
    var errorMessage: String = Constants.ERROR_MESSAGE

    @SerializedName("Information")
    var infoMessage: String = Constants.ERROR_MESSAGE

}