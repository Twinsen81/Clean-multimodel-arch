package com.example.scanner_example.fake

import com.example.core.di.app.CoreUtilsApi
import com.example.core.utils.SomeUtils
import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager


internal class FakeCoreUtilsComponentFactory : ComponentFactory<CoreUtilsApi> {
    override fun create(componentManager: ComponentManager): CoreUtilsApi {
        return object : CoreUtilsApi {
            override fun someUtils() = object : SomeUtils() {}
        }
    }
}