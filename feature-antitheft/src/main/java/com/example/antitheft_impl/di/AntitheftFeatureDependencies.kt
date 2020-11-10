package com.example.antitheft_impl.di

import com.example.core_db_api.data.DbClient
import com.example.core_network_api.data.HttpClient
import com.example.purchase_api.domain.PurchaseInteractor

interface AntitheftFeatureDependencies {
    fun dbClient(): DbClient
    fun httpClient(): HttpClient
    fun purchaseInteractor(): PurchaseInteractor
}