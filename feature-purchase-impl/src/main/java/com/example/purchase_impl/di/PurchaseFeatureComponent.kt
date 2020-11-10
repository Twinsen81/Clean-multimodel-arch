package com.example.purchase_impl.di

import com.example.core.di.general.PerFeature
import com.example.core_network_api.di.CoreNetworkApi
import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import com.example.module_injector.ComponentProperties
import com.example.module_injector.RootComponentManager
import com.example.purchase_api.PurchaseFeatureApi
import dagger.Component

@Component(dependencies = [PurchaseFeatureDependencies::class], modules = [PurchaseModule::class])
@PerFeature
internal abstract class PurchaseFeatureComponent : PurchaseFeatureApi {

    companion object {
        fun get(): PurchaseFeatureComponent =
                RootComponentManager.getComponent(PurchaseFeatureApi::class) as PurchaseFeatureComponent

        fun release() = RootComponentManager.releaseComponent(PurchaseFeatureApi::class)
    }
}

class PurchaseFeatureComponentFactory : ComponentFactory<PurchaseFeatureApi> {
    override fun create(componentManager: ComponentManager): PurchaseFeatureApi {
        return DaggerPurchaseFeatureComponent.builder()
                .purchaseFeatureDependencies(getDependencies(componentManager))
                .build()
    }

    private fun getDependencies(componentManager: ComponentManager): PurchaseFeatureDependencies {
        return object : PurchaseFeatureDependencies {
            override fun httpClient() = componentManager.getOrCreateComponent(
                    ComponentProperties(CoreNetworkApi::class)).httpClient()
        }
    }
}



