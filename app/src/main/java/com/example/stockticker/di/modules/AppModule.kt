package com.ankitsharma.androidkotlinboilerplate.di.modules

import android.app.Application
import android.content.Context
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
    fun provideContext(application: Application): Context = application
}