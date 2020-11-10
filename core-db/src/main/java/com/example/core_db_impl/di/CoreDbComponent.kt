package com.example.core_db_impl.di

import com.example.core_db_api.di.CoreDbApi
import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import com.example.module_injector.RootComponentManager
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class])
@Singleton
abstract class CoreDbComponent : CoreDbApi {
    companion object {
        fun get(): CoreDbComponent =
                RootComponentManager.getComponent(CoreDbApi::class) as CoreDbComponent

        fun release() = RootComponentManager.releaseComponent(CoreDbApi::class)
    }

    override fun isReleasable() = false
}

class CoreDbComponentFactory : ComponentFactory<CoreDbApi> {
    override fun create(componentManager: ComponentManager): CoreDbApi {
        return DaggerCoreDbComponent.builder().build()
    }
}