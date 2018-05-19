package com.example.stockticker.data.models

import com.github.mikephil.charting.data.Entry

class DeducedStockDetailsDTO {
    lateinit var highest: String
    lateinit var lowest: String
    lateinit var current: String
    lateinit var open: String
    lateinit var volume: String
    lateinit var symbol: String
    lateinit var graphicalInfo: List<Entry>
}