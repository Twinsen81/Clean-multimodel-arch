package com.example.scanner_impl.di

import com.example.core.di.app.CoreUtilsApi
import com.example.core.di.general.PerFeature
import com.example.core.utils.SomeUtils
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
import com.example.scanner_api.ScannerFeatureApi
import com.example.scanner_impl.presentation.view.ScannerActivity
import com.example.scanner_impl.presentation.view.ScannerMainFragment
import dagger.Component

@Component(
        modules = [ScannerFeatureModule::class, ScreenNavigationModule::class],
        dependencies = [ScannerFeatureDependencies::class]
)
@PerFeature
internal abstract class ScannerFeatureComponent : ScannerFeatureApi {
    internal abstract fun inject(scannerActivity: ScannerActivity)
    internal abstract fun inject(scannerMainFragment: ScannerMainFragment)

    companion object {
        fun get(): ScannerFeatureComponent =
                RootComponentManager.getComponent(ScannerFeatureApi::class) as ScannerFeatureComponent

        fun release() = RootComponentManager.releaseComponent(ScannerFeatureApi::class)
    }

    override fun isReleasable() = true
}

class ScannerFeatureComponentFactory : ComponentFactory<ScannerFeatureApi> {
    override fun create(componentManager: ComponentManager): ScannerFeatureApi {
        return DaggerScannerFeatureComponent.builder()
                .scannerFeatureDependencies(getDependencies(componentManager))
                .build()
    }

    private fun getDependencies(componentManager: ComponentManager): ScannerFeatureDependencies {
        return object : ScannerFeatureDependencies {
            override fun dbClient(): DbClient =
                    componentManager.getOrCreateComponent(ComponentProperties(CoreDbApi::class)).dbClient()

            override fun httpClient(): HttpClient =
                    componentManager.getOrCreateComponent(ComponentProperties(CoreNetworkApi::class)).httpClient()

            override fun someUtils(): SomeUtils =
                    componentManager.getOrCreateComponent(ComponentProperties(CoreUtilsApi::class)).someUtils()

            override fun purchaseInteractor(): PurchaseInteractor =
                    componentManager
                            .getOrCreateComponent(ComponentProperties(PurchaseFeatureApi::class))
                            .purchaseInteractor()
        }
    }
}

