package com.info.coinappwithcleanarch.data.cashed.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.CoinCashedItem

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(hCashedItem: CoinCashedItem): Long

    @Query("SELECT * from coins")
    suspend fun hGetAllCashedCoins(): List<CoinCashedItem>

}