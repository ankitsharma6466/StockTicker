package com.example.stockticker.data.services

import android.content.SharedPreferences
import com.example.stockticker.common.Constants
import com.example.stockticker.common.StockDataSyncHelper
import com.example.stockticker.data.repositories.StockRepository
import com.google.android.gms.gcm.GcmNetworkManager
import com.google.android.gms.gcm.GcmTaskService
import com.google.android.gms.gcm.TaskParams
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Service the will execute the next sync for current selected stock.
 *
 * Created by ankitsharma on 20/05/18.
 */
class StockSyncService: GcmTaskService() {

    @Inject
    lateinit var stockDataSyncHelper: StockDataSyncHelper

    @Inject
    lateinit var pref: SharedPreferences

    @Inject
    lateinit var repository: StockRepository

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onRunTask(p0: TaskParams?): Int {

        val symbol = pref.getString(Constants.CURRENT_SYMBOL, Constants.DEFAULT_SYMBOL)
        repository.getStockIntradayDetails(symbol)

        //schedule next
        stockDataSyncHelper.schedule()

        return GcmNetworkManager.RESULT_SUCCESS
    }
}