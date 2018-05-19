package com.example.stockticker.views.stockDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.stockticker.R
import com.example.stockticker.common.BaseActivity
import com.example.stockticker.databinding.ActivityStockDetailsBinding
import javax.inject.Inject

class StockDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var dataBinding: ActivityStockDetailsBinding
    lateinit var stockViewModel: StockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_details)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_stock_details)
        stockViewModel = ViewModelProviders.of(this, viewModelFactory).get(StockViewModel::class.java)

        dataBinding.stockViewModel = stockViewModel

        stockViewModel.loadStockInfo()

        subscribeToModel()
    }

    fun subscribeToModel() {

        stockViewModel.stockDetailsObservable.observe(this, Observer {

            stockViewModel.setLoader(false)

            if(it!!.isError) {
                //show error message
                stockViewModel.setErrorMessage(it.errorMessage)
            } else {
                stockViewModel.setDeducedStockDetailsDTO(it.data!!)
            }
        })
    }
}
