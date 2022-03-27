package com.info.coinappwithcleanarch.di

import android.content.Context
import androidx.room.Room
import com.info.coinappwithcleanarch.common.Constants.BASE_URL
import com.info.coinappwithcleanarch.common.Constants.COIN_DATABASE
import com.info.coinappwithcleanarch.data.cashed.roomdb.dao.CoinDao
import com.info.coinappwithcleanarch.data.cashed.roomdb.db.CoinDatabase
import com.info.coinappwithcleanarch.data.remote.retrofit.CoinApi
import com.info.coinappwithcleanarch.data.repository.CoinRepoImplementation
import com.info.coinappwithcleanarch.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }


    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): CoinDatabase {
        return Room
            .databaseBuilder(
                context,
                CoinDatabase::class.java,
                COIN_DATABASE
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(blogDatabase: CoinDatabase): CoinDao {
        return blogDatabase.coinDao()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(
        api: CoinApi,
        coinDao: CoinDao
    ): CoinRepository {
        return CoinRepoImplementation(api, coinDao)
    }

}