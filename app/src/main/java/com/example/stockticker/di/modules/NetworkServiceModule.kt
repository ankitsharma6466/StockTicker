package com.ankitsharma.androidkotlinboilerplate.di.modules

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

//    @Provides
//    @Singleton
//    fun provideGithubService(retrofit: Retrofit): GithubService = retrofit.create(GithubService::class.java)
}