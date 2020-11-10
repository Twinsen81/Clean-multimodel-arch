package com.example.core_network_impl.di

import com.example.core_network_api.di.CoreNetworkApi
import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import com.example.module_injector.RootComponentManager
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
internal abstract class CoreNetworkComponent : CoreNetworkApi {
    companion object {
        fun get(): CoreNetworkComponent =
                RootComponentManager.getComponent(CoreNetworkApi::class) as CoreNetworkComponent

        fun release() = RootComponentManager.releaseComponent(CoreNetworkApi::class)
    }
}

class CoreNetworkComponentFactory : ComponentFactory<CoreNetworkApi> {
    override fun create(componentManager: ComponentManager): CoreNetworkApi {
        return DaggerCoreNetworkComponent.builder().build()
    }
}