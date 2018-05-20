package com.example.stockticker.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.stockticker.data.models.DeducedStockDetailsDTO

/**
 * Created by ankitsharma on 20/05/18.
 */
@Dao
interface StockDetailsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(details: DeducedStockDetailsDTO)

    @Query("SELECT * from StockDetails WHERE symbol = :symbol")
    fun getStockDetails(symbol: String): DeducedStockDetailsDTO?
}