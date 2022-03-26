package com.info.coinappwithcleanarch.data.cashed.roomdb.db

import androidx.room.Database
import com.info.coinappwithcleanarch.data.cashed.roomdb.dao.CoinDao
import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.CoinCashedItem

@Database(entities = [CoinCashedItem::class], version = 1)
abstract class CoinDatabase {
    abstract fun coinDao(): CoinDao
}