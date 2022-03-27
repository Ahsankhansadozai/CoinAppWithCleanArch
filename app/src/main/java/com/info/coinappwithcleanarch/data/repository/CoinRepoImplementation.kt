package com.info.coinappwithcleanarch.data.repository

import com.info.coinappwithcleanarch.data.cashed.roomdb.dao.CoinDao
import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.CoinCashedItem
import com.info.coinappwithcleanarch.data.model.CoinItem
import com.info.coinappwithcleanarch.data.remote.retrofit.CoinApi
import com.info.coinappwithcleanarch.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinRepoImplementation @Inject constructor(
    private val coinApi: CoinApi,
    private val coinDao: CoinDao
) : CoinRepository {

    override suspend fun getCoinsFromNetwork(): List<CoinItem> {
        return coinApi.getData()
    }

    override suspend fun hInsetCoinToDataBase(hCashedItem: CoinCashedItem): Long {
        return withContext(Dispatchers.IO) {
            coinDao.insertCoin(hCashedItem)
        }

    }

    override suspend fun hGetAllCashedCoins(): List<CoinCashedItem> {
        return withContext(Dispatchers.IO) {
            coinDao.getAllCoins()
        }
    }
}