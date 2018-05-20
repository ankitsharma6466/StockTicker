package com.example.stockticker.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.example.stockticker.MyApplication
import com.example.stockticker.data.db.StockDetailsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * App level dependencies
 * Created by ankitsharma on 19/05/18.
 */
@Module(includes = arrayOf(NetworkServiceModule::class))
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: MyApplication): StockDetailsDatabase {
        return Room
                .databaseBuilder(application.applicationContext, StockDetailsDatabase::class.java, "StockDB")
                .build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: MyApplication): SharedPreferences {
        return application.getSharedPreferences("STOCK_PREF", Context.MODE_PRIVATE)
    }

}