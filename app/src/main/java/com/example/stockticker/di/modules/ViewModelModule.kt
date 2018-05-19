package com.ankitsharma.androidkotlinboilerplate.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.example.stockticker.common.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Provides map of all ViewModels and a ViewModelFactory for dependencies
 *
 * Created by ankitsharma on 19/05/18.
 */
@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}