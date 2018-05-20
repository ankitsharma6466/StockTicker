package com.example.stockticker.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.github.mikephil.charting.data.Entry
import io.reactivex.annotations.NonNull
import java.util.*

@Entity(tableName = "StockDetails")
class DeducedStockDetailsDTO {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "symbol")
    lateinit var symbol: String

    @ColumnInfo(name = "highest")
    lateinit var highest: String

    @ColumnInfo(name = "lowest")
    lateinit var lowest: String

    @ColumnInfo(name = "current")
    lateinit var current: String

    @ColumnInfo(name = "open")
    lateinit var open: String

    @ColumnInfo(name = "volume")
    lateinit var volume: String

    @ColumnInfo(name = "date")
    var date: Long = 0

    @Ignore
    lateinit var graphicalInfo: List<Entry>
}