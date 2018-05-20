package com.example.stockticker.data.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.stockticker.common.Constants
import com.example.stockticker.common.DataParsingUtil
import com.example.stockticker.common.DataWrapper
import com.example.stockticker.data.db.StockDetailsDatabase
import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.data.services.StockDataService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepository @Inject constructor(private val stockDataService: StockDataService,
                                          private val stockDetailsDatabase: StockDetailsDatabase) {

    fun getStockIntradayDetails(symbol: String): LiveData<DataWrapper<DeducedStockDetailsDTO>> {

        var stockInfoLiveData: MutableLiveData<DataWrapper<DeducedStockDetailsDTO>> = MutableLiveData()
        var dao = stockDetailsDatabase.stockDetailsDao()

        Observable.just(symbol)
                .subscribeOn(Schedulers.io())
                .map {
                    var cacheData = dao.getStockDetails(symbol)
                    ("cache >>>>>>>> ${cacheData.toString()}")
                    cacheData
                }.subscribe({
                    Timber.d("Data>>>>>>>> ${it.toString()}")
                    it!!.graphicalInfo = ArrayList()
                    stockInfoLiveData.postValue(DataWrapper(data = it))
                }, {
                    // do nothing
                    Timber.e(it)
                })

        //todo: also check for latest time to avoid iterations
        stockDataService.getDetails(symbol = symbol)
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    when {
                        (it.metaData != null && it.stockDetailItems != null) -> {

                            val deducedStockDetailsDTO = DataParsingUtil.getDeducedInfo(it.stockDetailItems!!)
                            deducedStockDetailsDTO.symbol = it.metaData!!.symbol

                            //save to database
                            saveDetails(deducedStockDetailsDTO)

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
                    Timber.e(it)
                    stockInfoLiveData.postValue(DataWrapper(isError = true, errorMessage = it.message!!))
                })

        return stockInfoLiveData
    }

    fun saveDetails(details: DeducedStockDetailsDTO) {

        Observable.just(details)
                .map {
                    stockDetailsDatabase.stockDetailsDao().insert(it)
                }.subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d("Successfully inserted")
                }, {
                    Timber.e(it)
                })
    }


}