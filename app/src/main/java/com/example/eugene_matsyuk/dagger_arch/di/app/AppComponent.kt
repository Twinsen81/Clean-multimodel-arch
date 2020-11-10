package com.example.eugene_matsyuk.dagger_arch.di.app

import com.example.antitheft_api.AntitheftFeatureApi
import com.example.antitheft_impl.di.AntitheftFeatureComponentFactory
import com.example.core.di.app.CoreUtilsApi
import com.example.core.di.app.CoreUtilsComponentFactory
import com.example.core_db_api.di.CoreDbApi
import com.example.core_db_impl.di.CoreDbComponentFactory
import com.example.core_network_api.di.CoreNetworkApi
import com.example.core_network_impl.di.CoreNetworkComponentFactory
import com.example.eugene_matsyuk.dagger_arch.DaggerArchApplication
import com.example.module_injector.RootComponentManager
import com.example.purchase_api.PurchaseFeatureApi
import com.example.purchase_impl.di.PurchaseFeatureComponentFactory
import com.example.scanner_api.ScannerFeatureApi
import com.example.scanner_impl.di.ScannerFeatureComponentFactory
import dagger.Component
import dagger.internal.Preconditions
import javax.inject.Singleton

@Component(modules = [GlobalNavigationModule::class, AppModule::class])
@Singleton
abstract class AppComponent {
    abstract fun inject(daggerArchApplication: DaggerArchApplication)
    abstract fun mainScreenComponent(): MainScreenComponent

    companion object {
        @Volatile
        private var instance: AppComponent? = null

        fun get(): AppComponent {
            return Preconditions.checkNotNull(instance,
                    "AppComponent is not initialized yet. Call init first.")!!
        }

        fun init(component: AppComponent) {
            require(instance == null) { "AppComponent is already initialized." }
            instance = component

            // Libs
            RootComponentManager.registerFactory(CoreDbComponentFactory(), CoreDbApi::class)
            RootComponentManager.registerFactory(CoreNetworkComponentFactory(), CoreNetworkApi::class)
            RootComponentManager.registerFactory(CoreUtilsComponentFactory(), CoreUtilsApi::class)

            // Features
            RootComponentManager.registerFactory(AntitheftFeatureComponentFactory(), AntitheftFeatureApi::class)
            RootComponentManager.registerFactory(PurchaseFeatureComponentFactory(), PurchaseFeatureApi::class)
            RootComponentManager.registerFactory(ScannerFeatureComponentFactory(), ScannerFeatureApi::class)
        }
    }
}