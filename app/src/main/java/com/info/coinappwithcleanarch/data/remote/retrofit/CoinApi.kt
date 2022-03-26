package com.info.coinappwithcleanarch.data.remote.retrofit

import com.info.coinappwithcleanarch.data.model.CoinItem
import retrofit2.http.GET

interface CoinApi {
    @GET("/v1/coins")
    suspend fun getData(): List<CoinItem>
}