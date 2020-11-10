package com.example.core.di.app

import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import com.example.module_injector.RootComponentManager
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
abstract class CoreUtilsComponent : CoreUtilsApi {
    companion object {
        fun get(): CoreUtilsComponent =
                RootComponentManager.getComponent(CoreUtilsApi::class) as CoreUtilsComponent

        fun release() = RootComponentManager.releaseComponent(CoreUtilsApi::class)
    }
}

class CoreUtilsComponentFactory : ComponentFactory<CoreUtilsApi> {
    override fun create(componentManager: ComponentManager): CoreUtilsApi {
        return DaggerCoreUtilsComponent.builder().build()
    }
}
