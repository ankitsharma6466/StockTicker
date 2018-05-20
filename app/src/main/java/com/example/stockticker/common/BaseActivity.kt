package com.example.stockticker.common

import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ankitsharma on 19/05/18.
 */
open class BaseActivity: DaggerAppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()

    fun getCompositeDisposable(): CompositeDisposable {

        if(compositeDisposable.isDisposed) compositeDisposable = CompositeDisposable()

        return compositeDisposable
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}