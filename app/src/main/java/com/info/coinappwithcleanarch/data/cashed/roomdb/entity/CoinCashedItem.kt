package com.info.coinappwithcleanarch.data.cashed.roomdb.entity

import androidx.room.Entity
import com.info.coinappwithcleanarch.domain.model.CoinModel

@Entity
data class CoinCashedItem(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinCashedItem.toCoinModel(): CoinModel {
    return CoinModel(
        id = id,
        is_active = is_active,
        is_new = is_new,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type
    )
}