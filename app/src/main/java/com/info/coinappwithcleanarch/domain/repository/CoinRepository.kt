package com.info.coinappwithcleanarch.domain.repository

import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.CoinCashedItem
import com.info.coinappwithcleanarch.data.model.CoinItem

interface CoinRepository {
    suspend fun getCoinsFromNetwork(): List<CoinItem>

    suspend fun hInsetCoinToDataBase(hCashedItem: CoinCashedItem): Long

    suspend fun hGetAllCashedCoins(): List<CoinCashedItem>
}