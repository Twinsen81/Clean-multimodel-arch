package com.example.module_injector

internal class ComponentHolder<T : ComponentApi>(private val componentFactory: ComponentFactory<T>,
                                                 private val componentsManager: ComponentManager) {

    private var component: T? = null

    private var ownerCount = 0

    @Synchronized
    fun getOrCreate(): T {
        if (component == null) {
            component = componentFactory.create(componentsManager)
        }
        if (component!!.isReleasable()) {
            ownerCount++
        }
        return component!!
    }

    @Synchronized
    fun getWithoutRef(): T {
        if (component == null) {
            throw IllegalStateException("Component isn't created yet!")
        }
        return component!!
    }

    @Synchronized
    fun release() {
        if (component!!.isReleasable()) {
            if (component == null) return
            ownerCount--
            if (ownerCount <= 0) {
                releaseComponent()
            }
        }
    }

    private fun releaseComponent() {
        component = null
    }
}