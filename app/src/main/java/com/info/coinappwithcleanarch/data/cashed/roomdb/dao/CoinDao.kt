package com.info.coinappwithcleanarch.data.cashed.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.CoinCashedItem

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(): Long

    @Query("SELECT * from CoinCashedItem")
    suspend fun getAllCoins(): List<CoinCashedItem>

}