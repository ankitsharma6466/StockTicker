package com.example.stockticker.di.modules

import com.example.stockticker.views.settings.SettingsActivity
import com.example.stockticker.views.stockDetails.StockDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Contains all activities to be bound to application dependency graph
 *
 * Created by ankitsharma on 19/05/18.
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bindStockDetailsActivity(): StockDetailsActivity

    @ContributesAndroidInjector
    internal abstract fun bindSettingsActivity(): SettingsActivity
}