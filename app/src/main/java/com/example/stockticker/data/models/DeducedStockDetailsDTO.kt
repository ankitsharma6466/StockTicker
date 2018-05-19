package com.example.stockticker.data.models

import java.util.*
import kotlin.collections.LinkedHashMap

class DeducedStockDetailsDTO {
    lateinit var highest: String
    lateinit var lowest: String
    lateinit var current: String
    lateinit var open: String
    lateinit var volume: String
    lateinit var calendar: Calendar
    lateinit var graphicalInfo: LinkedHashMap<String, Float>
}