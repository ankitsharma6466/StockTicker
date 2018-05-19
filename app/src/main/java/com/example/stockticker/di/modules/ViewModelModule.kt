package com.example.stockticker.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.stockticker.common.ViewModelFactory
import com.example.stockticker.di.annotations.ViewModelKey
import com.example.stockticker.views.stockDetails.StockViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides map of all ViewModels and a ViewModelFactory for dependencies
 *
 * Created by ankitsharma on 19/05/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StockViewModel::class)
    abstract fun bindStockViewModel(stockViewModel: StockViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}