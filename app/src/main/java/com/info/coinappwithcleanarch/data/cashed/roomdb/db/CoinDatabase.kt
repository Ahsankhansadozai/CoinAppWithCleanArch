package com.info.coinappwithcleanarch.data.cashed.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.info.coinappwithcleanarch.data.cashed.roomdb.dao.CoinDao
import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.CoinCashedItem

@Database(entities = [CoinCashedItem::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}