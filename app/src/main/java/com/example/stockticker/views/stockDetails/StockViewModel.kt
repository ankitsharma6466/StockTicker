package com.example.stockticker.views.stockDetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.repositories.StockRepository
import javax.inject.Inject

class StockViewModel @Inject constructor(private val stockRepository: StockRepository): ViewModel() {

    lateinit var deducedStockDetailsDTO: LiveData<DeducedStockDetailsDTO>

    fun loadStockInfo() {
        deducedStockDetailsDTO = stockRepository.getStockIntradayDetails()
    }
}