package com.example.stockticker.data.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.stockticker.common.Constants
import com.example.stockticker.common.DataParsingUtil
import com.example.stockticker.common.DataWrapper
import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.services.StockDataService
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(private val stockDataService: StockDataService) {

    fun getStockIntradayDetails(): LiveData<DataWrapper<DeducedStockDetailsDTO>> {

        var stockInfoLiveData: MutableLiveData<DataWrapper<DeducedStockDetailsDTO>> = MutableLiveData()
        //todo: add cache check here

        //todo: also check for latest time to avoid iterations
        stockDataService.getDetails(symbol = "GOOG")
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    when {
                        (it.metaData != null && it.stockDetailItems != null) -> {
                            val deducedStockDetailsDTO = DataParsingUtil.getDeducedInfo(it.stockDetailItems!!)
                            DataWrapper(data = deducedStockDetailsDTO)
                        }
                        (it.infoMessage != null) -> DataWrapper(isError = true, errorMessage = it.infoMessage!!)
                        (it.errorMessage != null) -> DataWrapper(isError = true, errorMessage = it.errorMessage!!)
                        else -> DataWrapper(isError = true)
                    }
                }
                .subscribe({
                    stockInfoLiveData.postValue(it)
                }, {
                    stockInfoLiveData.postValue(DataWrapper(isError = true, errorMessage = it.message!!))
                })

        return stockInfoLiveData
    }


}