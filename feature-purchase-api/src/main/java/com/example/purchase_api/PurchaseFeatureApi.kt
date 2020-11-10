package com.example.purchase_api

import com.example.module_injector.ComponentApi
import com.example.purchase_api.domain.PurchaseInteractor

interface PurchaseFeatureApi : ComponentApi {
    fun purchaseInteractor(): PurchaseInteractor
}