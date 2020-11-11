package com.example.module_injector

/**
 * Фабрика для создания компонента, реализующего интерфейс [T].
 * Фабрика всегда возвращает новый экземпляр компонента
 */
interface ComponentFactory<T : ComponentApi> {

    /**
     * Создать новый экземпляр компонента, реализующего интерфейс [T].
     * @param componentManager менеджер компонентов, через который необходимо получить зависимости,
     * требующиеся для создания экземпляра компонента
     */
    fun create(componentManager: ComponentManager): T
}