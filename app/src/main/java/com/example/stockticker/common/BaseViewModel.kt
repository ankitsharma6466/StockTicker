package com.example.stockticker.common

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

open class BaseViewModel: ViewModel() {

    val loader: ObservableField<Boolean> = ObservableField()
    val errorMessage: ObservableField<String> = ObservableField()
    val isError: ObservableField<Boolean> = ObservableField()

    fun setLoader(show: Boolean) {
        loader.set(show)
    }

    fun setErrorMessage(show: Boolean, msg: String = Constants.ERROR_MESSAGE) {
        isError.set(show)
        errorMessage.set(msg)
    }
}