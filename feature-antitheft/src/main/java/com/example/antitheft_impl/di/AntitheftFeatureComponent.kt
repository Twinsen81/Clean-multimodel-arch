package com.example.antitheft_impl.di

import com.example.antitheft_api.AntitheftFeatureApi
import com.example.antitheft_impl.presentation.view.AntitheftActivity
import com.example.antitheft_impl.presentation.view.AntitheftMainFragment
import com.example.core.di.general.PerFeature
import com.example.core_db_api.data.DbClient
import com.example.core_db_api.di.CoreDbApi
import com.example.core_network_api.data.HttpClient
import com.example.core_network_api.di.CoreNetworkApi
import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import com.example.module_injector.ComponentProperties
import com.example.module_injector.RootComponentManager
import com.example.purchase_api.PurchaseFeatureApi
import com.example.purchase_api.domain.PurchaseInteractor
import dagger.Component

@Component(
        modules = [AntitheftFeatureModule::class, AntitheftNavigationModule::class],
        dependencies = [AntitheftFeatureDependencies::class]
)
@PerFeature
internal abstract class AntitheftFeatureComponent : AntitheftFeatureApi {

    internal abstract fun inject(antitheftActivity: AntitheftActivity)
    internal abstract fun inject(antitheftMainFragment: AntitheftMainFragment)

    companion object {
        fun get(): AntitheftFeatureComponent =
                RootComponentManager.getComponent(AntitheftFeatureApi::class) as AntitheftFeatureComponent

        fun release() = RootComponentManager.releaseComponent(AntitheftFeatureApi::class)
    }
}

class AntitheftFeatureComponentFactory : ComponentFactory<AntitheftFeatureApi> {
    override fun create(componentManager: ComponentManager): AntitheftFeatureApi {
        return DaggerAntitheftFeatureComponent.builder()
                .antitheftFeatureDependencies(getDependencies(componentManager))
                .build()
    }

    private fun getDependencies(componentManager: ComponentManager): AntitheftFeatureDependencies {
        return object : AntitheftFeatureDependencies {
            override fun dbClient(): DbClient =
                    componentManager.getOrCreateComponent(ComponentProperties(CoreDbApi::class)).dbClient()

            override fun httpClient(): HttpClient =
                    componentManager.getOrCreateComponent(ComponentProperties(CoreNetworkApi::class)).httpClient()

            override fun purchaseInteractor(): PurchaseInteractor =
                    componentManager
                            .getOrCreateComponent(ComponentProperties(PurchaseFeatureApi::class))
                            .purchaseInteractor()
        }
    }

}

