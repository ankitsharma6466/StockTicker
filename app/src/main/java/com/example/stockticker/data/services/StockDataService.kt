package com.example.stockticker.data.services

import com.example.stockticker.BuildConfig
import com.example.stockticker.data.models.ResponseStockIntraday
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Provides methods for network bound resources
 *
 * Created by ankitsharma on 19/05/18.
 */
interface StockDataService {

    @GET("query?apikey=${BuildConfig.ALPHAVANTAGE_KEY}")
    fun getDetails(@Query("function") function: String = "TIME_SERIES_INTRADAY",
                    @Query("symbol") symbol: String,
                    @Query("interval") interval: String = "5min",
                    @Query("outputsize") outputsize: String = "compact"): Single<ResponseStockIntraday>
}