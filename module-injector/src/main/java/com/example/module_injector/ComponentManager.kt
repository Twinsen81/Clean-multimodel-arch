package com.example.module_injector

import kotlin.reflect.KClass

interface ComponentManager {
    fun <T : ComponentApi> registerFactory(factory: ComponentFactory<T>, klass: KClass<T>)
    fun <T : ComponentApi> getOrCreateComponent(properties: ComponentProperties<T>): T
    fun <T : ComponentApi> getComponent(klass: KClass<T>): T
    fun <T : ComponentApi> releaseComponent(klass: KClass<T>)
}

interface ComponentFactory<T : ComponentApi> {
    fun create(componentManager: ComponentManager): T
}

interface ComponentApi {
    fun isReleasable(): Boolean = true
}

data class ComponentProperties<T : ComponentApi>(val klass: KClass<T>)
