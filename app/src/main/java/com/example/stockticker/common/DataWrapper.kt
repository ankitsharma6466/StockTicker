package com.example.stockticker.common

/**
 * Created by ankitsharma on 19/05/18.
 */
class DataWrapper<T>(
        var data: T? = null,
        var isError: Boolean = false,
        var errorMessage: String = Constants.ERROR_MESSAGE)