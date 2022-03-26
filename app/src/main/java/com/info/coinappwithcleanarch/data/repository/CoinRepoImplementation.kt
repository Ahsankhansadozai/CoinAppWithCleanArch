package com.info.coinappwithcleanarch.data.repository

import com.info.coinappwithcleanarch.data.model.CoinItem
import com.info.coinappwithcleanarch.data.remote.retrofit.CoinApi
import com.info.coinappwithcleanarch.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepoImplementation @Inject constructor(
    private val coinApi: CoinApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinItem> {
        return coinApi.getData()
    }
}