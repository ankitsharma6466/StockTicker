package com.example.stockticker.views.stockDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import com.example.stockticker.R
import com.example.stockticker.common.BaseActivity
import com.example.stockticker.databinding.ActivityStockDetailsBinding
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_stock_details.*
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

        setupChart()
        dataBinding.stockViewModel = stockViewModel

        stockViewModel.loadStockInfo()

        subscribeToModel()
    }

    fun setupChart() {
        chartView.setTouchEnabled(false)
        chartView.setPinchZoom(false)
        chartView.contentDescription = ""

        var description = Description()
        description.isEnabled = false
        chartView.description = description

        chartView.setNoDataText("Loading data...")

        chartView.legend.isEnabled = false

        var xAxis = chartView.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawLabels(false)
        xAxis.setDrawLabels(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        var yAxis = chartView.axisRight
        yAxis.setDrawLabels(false)
        yAxis.setDrawGridLines(false)

    }

    fun subscribeToModel() {

        stockViewModel.stockDetailsObservable.observe(this, Observer {

            stockViewModel.setLoader(false)

            if(it!!.isError) {
                //show error message
                stockViewModel.setErrorMessage(it.errorMessage)
            } else {
                stockViewModel.setDeducedStockDetailsDTO(it.data!!)
                updateChart(it.data!!.graphicalInfo)
            }
        })
    }

    fun updateChart(chartData: List<Entry>){

        var data = chartData.reversed()

        var set = LineDataSet(data, "Price")
        set.setDrawCircles(false)
        set.setDrawValues(false)
        set.setColor(Color.GRAY, 255)
        set.lineWidth = 2F

        var dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set)
        var lineData = LineData(dataSets)

        chartView.data = lineData
        chartView.invalidate()
    }
}
