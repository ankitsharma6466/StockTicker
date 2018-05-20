package com.example.stockticker.common

import com.example.stockticker.data.services.StockSyncService
import com.google.android.gms.gcm.GcmNetworkManager
import com.google.android.gms.gcm.OneoffTask
import com.google.android.gms.gcm.Task
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class StockDataSyncHelper @Inject constructor(private val manager: GcmNetworkManager) {

    fun schedule() {

        var task = OneoffTask.Builder()
                .setTag(Constants.TAG_STOCK_SYNC_SERVICE)
                .setExecutionWindow(getNextExecutionTime(), getNextExecutionTime() + 600)
                .setService(StockSyncService::class.java)
                .setRequiredNetwork(Task.NETWORK_STATE_UNMETERED)
                .build()

        //in case opened simultaneously multiple times
        manager.cancelAllTasks(StockSyncService::class.java)

        manager.schedule(task)
    }

    fun getNextExecutionTime(): Long {

        var cal = Calendar.getInstance()

        var marketTimings = StockMarketHelper.getMarketTimings(cal.get(Calendar.DATE))

        if(cal.timeInMillis > marketTimings.closeTime) {

            //market is closed
            //fireoff next event when market opens wrt to todays time
            var futureOpenTime = StockMarketHelper.getFutureMarketOpenTime()

            //return diff in current time and future open time
            return (futureOpenTime - cal.timeInMillis)/1000

        } else {
            //market is open, fireoff in one hour
            return 60*62 // using a 2 min ahead time
        }
    }
}