package com.example.stockticker.views.stockDetails

import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import com.example.stockticker.common.BaseViewModel
import com.example.stockticker.common.DataWrapper
import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.repositories.StockRepository
import javax.inject.Inject

class StockViewModel @Inject constructor(private val stockRepository: StockRepository): BaseViewModel() {

    lateinit var stockDetailsObservable: LiveData<DataWrapper<DeducedStockDetailsDTO>>

    var deducedStockDetailsDTO: ObservableField<DeducedStockDetailsDTO> = ObservableField()

    fun loadStockInfo() {
        setLoader(true)
        stockDetailsObservable = stockRepository.getStockIntradayDetails()
    }

    fun setDeducedStockDetailsDTO(details: DeducedStockDetailsDTO) {
        this.deducedStockDetailsDTO.set(details)
    }
}