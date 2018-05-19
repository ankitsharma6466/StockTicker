package com.example.stockticker.common

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

open class BaseViewModel: ViewModel() {
    val loader: ObservableField<Boolean> = ObservableField()
    val errorMessage: ObservableField<String> = ObservableField()

    fun setLoader(show: Boolean) {
        loader.set(show)
    }

    fun setErrorMessage(msg: String) {
        errorMessage.set(msg)
    }
}