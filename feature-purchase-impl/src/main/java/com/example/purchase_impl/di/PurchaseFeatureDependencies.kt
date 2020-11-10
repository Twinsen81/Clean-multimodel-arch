package com.example.purchase_impl.di

import com.example.core_network_api.data.HttpClient

interface PurchaseFeatureDependencies {
    fun httpClient(): HttpClient
}