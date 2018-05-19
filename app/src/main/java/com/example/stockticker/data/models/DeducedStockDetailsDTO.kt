package com.example.stockticker.data.models

class DeducedStockDetailsDTO {

    var highest: Float = 0F
    var lowest: Float = 0F
    var current: Float = 0F
    var open: Float = 0F
    lateinit var graphicalInfo: HashMap<String, Float>
}