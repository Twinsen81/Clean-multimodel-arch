package com.example.module_injector

/**
 * Хранит ссылку на компонент. Считает количество ссылок, выданных клиентам.
 * Если компонент ещё не создан - создаёт его.
 * Зануляет ссылку (высвобождая компонент для GC) если компонент больше не используется клиентами.
 */
internal class ComponentHolder<T : ComponentApi>(private val componentFactory: ComponentFactory<T>,
                                                 private val componentsManager: ComponentManager) {
    /**
     * Ссылка на компонент
     */
    private var component: T? = null

    /**
     * Счётчик "клиентов" компонента - классов, которые содержат ссылку на данный компонент
     */
    private var clientCount = 0

    /**
     * Вернуть ссылку на компонент или создать его с помощью фабрики.
     * Если компонент поддерживает "высвобождение" (для сборки GC-ом), то для него
     * инкрементируется счётчик клиентов
     */
    @Synchronized
    fun getOrCreate(): T {
        if (component == null) {
            component = componentFactory.create(componentsManager)
        }
        if (component!!.isReleasable()) {
            clientCount++
        }
        return component!!
    }

    /**
     * Вернуть ссылку на компонент (БЕЗ увеличения счётчика клиентов).
     * Если компонент ещё создан, то будет брошено исключение IllegalStateException
     */
    @Synchronized
    fun getWithoutRef(): T {
        if (component == null) {
            throw IllegalStateException("Component isn't created yet!")
        }
        return component!!
    }

    /**
     * "Освободить" компонент для GC, т.к. он больше не используется данным клиентом.
     * Уменьшает счётчик клиентов компонента. Если клиентов = 0, то компонент
     * освобождается ([component] = null), и доступен для сбора GC-ом
     */
    @Synchronized
    fun release() {
        if (component!!.isReleasable()) {
            if (component == null) return
            clientCount--
            if (clientCount <= 0) {
                releaseComponent()
            }
        }
    }

    /**
     * Занулить ссылку на компонент. Компонент становится доступным для сбора GC-ом (если на него
     * нет других ссылок, полученных не через [ComponentHolder]
     */
    @Throws
    private fun releaseComponent() {
        component = null
    }
}