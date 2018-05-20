package com.example.stockticker.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.stockticker.data.models.DeducedStockDetailsDTO

@Database(entities = [DeducedStockDetailsDTO::class], version = 1, exportSchema = false)
abstract class StockDetailsDatabase: RoomDatabase() {
    abstract fun stockDetailsDao(): StockDetailsDAO
}