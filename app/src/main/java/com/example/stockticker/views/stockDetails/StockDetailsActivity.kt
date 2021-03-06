package com.example.stockticker.views.stockDetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.stockticker.R
import com.example.stockticker.common.BaseActivity
import com.example.stockticker.common.DataWrapper
import com.example.stockticker.common.StockDataSyncHelper
import com.example.stockticker.data.events.SymbolChangedEvent
import com.example.stockticker.data.models.DeducedStockDetailsDTO
import com.example.stockticker.databinding.ActivityStockDetailsBinding
import com.example.stockticker.views.settings.SettingsActivity
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.android.gms.gcm.GcmNetworkManager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_stock_details.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StockDetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var stockDataSyncHelper: StockDataSyncHelper

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

        stockDataSyncHelper.schedule()

        startMonitoring()
    }

    fun startMonitoring() {

        getCompositeDisposable().add(
                Observable
                        .interval(5, 10, TimeUnit.MINUTES)
                        .subscribe({
                            stockViewModel.loadStockInfo()
                            subscribeToModel()
                        }, {
                            Timber.e(it)
                        })
        )
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
                stockViewModel.setErrorMessage(true, it.errorMessage)
            } else {
                stockViewModel.setDeducedStockDetailsDTO(it.data!!)
                stockViewModel.setLastUpdatedTimeStr(it.data!!.date)
                updateChart(it.data!!.graphicalInfo)
                stockViewModel.setErrorMessage(show = false)
            }
        })
    }

    fun updateChart(chartData: List<Entry>){

        if(chartData.isNotEmpty()) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = MenuInflater(this)
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.menu_settings) {
            gotoSettings()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun gotoSettings() {
        var settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onSymbolChange(event: SymbolChangedEvent) {
        EventBus.getDefault().removeStickyEvent(event)
        stockViewModel.loadStockInfo()
        subscribeToModel()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
