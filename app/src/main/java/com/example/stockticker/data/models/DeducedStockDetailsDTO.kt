package com.example.stockticker.data.models

import java.util.*

class DeducedStockDetailsDTO {
    lateinit var highest: String
    lateinit var lowest: String
    lateinit var current: String
    lateinit var open: String
    lateinit var volume: String
    lateinit var calendar: Calendar
    lateinit var graphicalInfo: HashMap<String, Float>
}