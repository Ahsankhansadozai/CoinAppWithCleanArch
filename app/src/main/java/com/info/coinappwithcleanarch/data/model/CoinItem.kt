package com.info.coinappwithcleanarch.data.model
import com.info.coinappwithcleanarch.data.cashed.roomdb.entity.CoinCashedItem

data class CoinItem(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinItem.toCoinCashedItem(): CoinCashedItem {
    return CoinCashedItem(
        id = id,
        is_active = is_active,
        is_new = is_new,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type
    )
}