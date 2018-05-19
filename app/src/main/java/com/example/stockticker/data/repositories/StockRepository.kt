package com.example.stockticker.data.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.stockticker.DataParsingUtils
import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.services.StockDataService
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(private val stockDataService: StockDataService) {

    fun getStockIntradayDetails(): LiveData<DeducedStockDetailsDTO> {

        var stockInfoLiveData: MutableLiveData<DeducedStockDetailsDTO> = MutableLiveData()

        //todo: add cache check here

        //todo: also check for latest time to avoid iterations

        stockDataService.getDetails(symbol = "GOOG")
                .observeOn(AndroidSchedulers.mainThread())
                .map {

                    if(it.metaData != null && it.stockDetailItems != null) {
                        var deducedStockDetailsDTO = DataParsingUtils.getDeducedInfo(it.stockDetailItems!!)
                    } else {
                        //send back error message or info
                    }
                }
                .subscribe({
                    //map it and extract info
                }, {

                })

        return stockInfoLiveData
    }


}