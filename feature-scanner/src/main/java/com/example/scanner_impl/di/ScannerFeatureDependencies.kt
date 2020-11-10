package com.example.scanner_impl.di

import com.example.core.utils.SomeUtils
import com.example.core_db_api.data.DbClient
import com.example.core_network_api.data.HttpClient
import com.example.purchase_api.domain.PurchaseInteractor

internal interface ScannerFeatureDependencies {
    fun dbClient(): DbClient
    fun httpClient(): HttpClient
    fun someUtils(): SomeUtils
    fun purchaseInteractor(): PurchaseInteractor
}