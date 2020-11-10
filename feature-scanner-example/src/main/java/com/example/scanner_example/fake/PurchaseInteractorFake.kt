package com.example.scanner_example.fake

import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import com.example.purchase_api.PurchaseFeatureApi
import com.example.purchase_api.domain.PurchaseInteractor
import com.example.purchase_api.domain.models.PurchaseModel
import io.reactivex.Single
import java.util.concurrent.TimeUnit

internal class PurchaseInteractorFake : PurchaseInteractor {
    override fun makePurchase(): Single<PurchaseModel> {
        return Single.timer(100, TimeUnit.MILLISECONDS)
                .map { aLong: Long -> PurchaseModel() }
    }
}

internal class FakePurchaseFeatureComponentFactory : ComponentFactory<PurchaseFeatureApi> {
    override fun create(componentManager: ComponentManager): PurchaseFeatureApi {
        return object : PurchaseFeatureApi {
            override fun purchaseInteractor() = PurchaseInteractorFake()
        }
    }
}