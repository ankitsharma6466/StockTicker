package com.example.stockticker.di.modules

import com.example.stockticker.data.services.StockDataService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Provides network services for data
 *
 * Created by ankitsharma on 19/05/18.
 */
@Module(includes = arrayOf(NetworkModule::class))
class NetworkServiceModule {

    @Provides
    @Singleton
    fun provideGithubService(retrofit: Retrofit): StockDataService = retrofit.create(StockDataService::class.java)
}