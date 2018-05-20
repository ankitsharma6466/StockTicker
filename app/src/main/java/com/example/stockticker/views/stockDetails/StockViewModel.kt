package com.example.stockticker.views.stockDetails

import android.arch.lifecycle.LiveData
import android.content.SharedPreferences
import android.databinding.ObservableField
import com.example.stockticker.common.BaseViewModel
import com.example.stockticker.common.Constants
import com.example.stockticker.common.DataWrapper
import com.example.stockticker.common.StockMarketHelper
import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.repositories.StockRepository
import javax.inject.Inject

class StockViewModel @Inject constructor(private val stockRepository: StockRepository, var pref: SharedPreferences): BaseViewModel() {

    lateinit var stockDetailsObservable: LiveData<DataWrapper<DeducedStockDetailsDTO>>

    var deducedStockDetailsDTO: ObservableField<DeducedStockDetailsDTO> = ObservableField()
    var lastUpdatedTimeStr: ObservableField<String> = ObservableField()

    fun loadStockInfo() {
        setLoader(true)
        val symbol = pref.getString(Constants.CURRENT_SYMBOL, Constants.DEFAULT_SYMBOL)
        stockDetailsObservable = stockRepository.getStockIntradayDetails(symbol)
    }

    fun setDeducedStockDetailsDTO(details: DeducedStockDetailsDTO) {
        this.deducedStockDetailsDTO.set(details)
    }

    fun setLastUpdatedTimeStr(time: Long) {
        this.lastUpdatedTimeStr.set(StockMarketHelper.getLastUpdatedTimeStr(time))
    }
}