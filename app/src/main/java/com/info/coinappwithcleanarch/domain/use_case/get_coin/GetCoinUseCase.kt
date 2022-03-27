package com.info.coinappwithcleanarch.domain.use_case.get_coin

import com.info.coinappwithcleanarch.common.Resource
import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.toCoinModel
import com.info.coinappwithcleanarch.data.model.toCoinCashedItem
import com.info.coinappwithcleanarch.domain.model.CoinModel
import com.info.coinappwithcleanarch.domain.repository.CoinRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<CoinModel>>> = flow {
        emit(Resource.Loading<List<CoinModel>>())
        delay(1000)
        try {
            val coins = repository.getCoinsFromNetwork().map { it.toCoinCashedItem() }

            for (coin in coins) {
                repository.hInsetCoinToDataBase(coin)
            }

            val cashedCoins = repository.hGetAllCashedCoins()

            emit(Resource.Success<List<CoinModel>>(cashedCoins.map { it.toCoinModel() }))

        } catch (e: HttpException) {
            emit(
                Resource.Error<List<CoinModel>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )

        } catch (e: IOException) {
            emit(
                Resource.Error<List<CoinModel>>(
                    e.localizedMessage ?: "Couldn't reach server. Check internet Connection"
                )
            )
        }


    }
}