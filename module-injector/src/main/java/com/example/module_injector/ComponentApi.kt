package com.example.module_injector

/**
 * Переиспользуемый компонент приложения
 */
interface ComponentApi {

    /**
     * Может ли менеджер компонентов удалить ссылку на данный компонент после того
     * как он перестанет быть нужен классам-клиентам?
     *
     * true - если компонент более не используется, можно занулить ссылку на него,
     * чтобы его скушал GC
     *
     * false - после первого создания, компонент навсегда остается в памяти приложения,
     * и не доступен для GC
     */
    fun isReleasable(): Boolean = true
}