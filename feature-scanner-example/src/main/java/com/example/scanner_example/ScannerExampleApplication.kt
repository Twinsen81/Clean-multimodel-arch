package com.example.scanner_example

import android.app.Application
import com.example.core.di.app.CoreUtilsApi
import com.example.core_db_api.di.CoreDbApi
import com.example.core_network_api.di.CoreNetworkApi
import com.example.module_injector.ComponentProperties
import com.example.module_injector.RootComponentManager
import com.example.purchase_api.PurchaseFeatureApi
import com.example.scanner_api.ScannerFeatureApi
import com.example.scanner_example.fake.FakeCoreDbComponentFactory
import com.example.scanner_example.fake.FakeCoreNetworkComponentFactory
import com.example.scanner_example.fake.FakeCoreUtilsComponentFactory
import com.example.scanner_example.fake.FakePurchaseFeatureComponentFactory
import com.example.scanner_impl.di.ScannerFeatureComponentFactory

class ScannerExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Libs
        RootComponentManager.registerFactory(FakeCoreDbComponentFactory(), CoreDbApi::class)
        RootComponentManager.registerFactory(FakeCoreNetworkComponentFactory(), CoreNetworkApi::class)
        RootComponentManager.registerFactory(FakeCoreUtilsComponentFactory(), CoreUtilsApi::class)

        // Features
        RootComponentManager.registerFactory(FakePurchaseFeatureComponentFactory(), PurchaseFeatureApi::class)
        RootComponentManager.registerFactory(ScannerFeatureComponentFactory(), ScannerFeatureApi::class)

        // Create the scanner component
        RootComponentManager.getOrCreateComponent(ComponentProperties(ScannerFeatureApi::class))
    }
}