package com.example.module_injector

import kotlin.reflect.KClass

object RootComponentManager : ComponentManager {

    private val holderStorage = mutableMapOf<String, ComponentHolder<out ComponentApi>>()
    private val factoryStorage = mutableMapOf<String, ComponentFactory<out ComponentApi>>()


    override fun <T : ComponentApi> registerFactory(factory: ComponentFactory<T>, klass: KClass<T>) {
        factoryStorage[klass.simpleName!!] = factory
    }

    /**
     * Получить созданный ранее компонент или создать новый.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ComponentApi> getOrCreateComponent(properties: ComponentProperties<T>): T {
        return getComponentHolder(properties.klass).getOrCreate()
    }

    /**
     * Получить созданный ранее компонент.
     * Если он ещё не создавался, то будет брошено исключение IllegalStateException
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ComponentApi> getComponent(klass: KClass<T>): T {
        if (!factoryStorage.contains(klass.simpleName!!))
            throw IllegalStateException("Factory for ${klass.simpleName!!} not registered")
        return getComponentHolder(klass).getWithoutRef()
    }

    override fun <T : ComponentApi> releaseComponent(klass: KClass<T>) {
        getComponentHolder(klass).release()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : ComponentApi> getComponentHolder(klass: KClass<T>): ComponentHolder<T> {
        val key = klass.simpleName!!
        return holderStorage.getOrElse(key) {
            val componentHolder = ComponentHolder(getFactoryForClass(klass), this)
            holderStorage[key] = componentHolder
            return@getOrElse componentHolder
        } as ComponentHolder<T>
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : ComponentApi> getFactoryForClass(klass: KClass<T>): ComponentFactory<T> {
        if (!factoryStorage.contains(klass.simpleName!!))
            throw IllegalStateException("Factory for ${klass.simpleName!!} not registered")
        return factoryStorage[klass.simpleName!!] as ComponentFactory<T>
    }
}