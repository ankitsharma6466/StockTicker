package com.example.stockticker.common

class DataWrapper<T>(
        var data: T? = null,
        var isError: Boolean = false,
        var errorMessage: String = Constants.ERROR_MESSAGE)