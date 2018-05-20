package com.example.stockticker.di.modules

import com.example.stockticker.common.StockDataSyncHelper
import com.example.stockticker.data.services.StockSyncService
import com.example.stockticker.views.settings.SettingsActivity
import com.example.stockticker.views.stockDetails.StockDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contains all services to be bound to application dependency graph
 *
 * Created by ankitsharma on 20/05/18.
 */
@Module
abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bindStockSyncService(): StockSyncService
}