package com.info.coinappwithcleanarch.presentation.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.coinappwithcleanarch.common.Resource
import com.info.coinappwithcleanarch.domain.model.CoinModel
import com.info.coinappwithcleanarch.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<CoinModel>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<CoinModel>>> get() = _dataState


    fun setStateEvent(mainStateEvent: MainStateEvent) {

        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetCoinsEvent -> {
                    getCoinUseCase().onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                Log.d("coin", "")
                            }

                            is Resource.Loading -> {

                            }

                            is Resource.Error -> {

                            }

                        }
                    }

                }

                MainStateEvent.None -> {
                    // who cares
                }
            }
        }

    }
}

sealed class MainStateEvent {

    object GetCoinsEvent : MainStateEvent()

    object None : MainStateEvent()
}