package com.info.coinappwithcleanarch.presentation.view.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.info.coinappwithcleanarch.databinding.ActivityMainBinding
import com.info.coinappwithcleanarch.domain.model.CoinModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hSubscribeObservers()

        viewModel.setStateEvent(MainStateEvent.GetCoinsEvent)


    }

    private fun hSubscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    displayProgressBar(false)
                    appendCoinsName(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)

                }

                is DataState.Loading -> {
                    displayProgressBar(true)

                }

            }

        }
    }

    private fun appendCoinsName(coins: List<CoinModel>) {
        val sb = StringBuilder()
        for (coin in coins) {
            sb.append(coin.name + "\n")
        }
        binding.text.text = sb.toString()
    }


    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?) {
        if (message != null) binding.text.text = message else binding.text.text = "Unknown error."
    }

}

