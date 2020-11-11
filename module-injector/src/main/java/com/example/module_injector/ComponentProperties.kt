package com.example.module_injector

import kotlin.reflect.KClass

/**
 * Определяет свойства компонента, который необходимо получить у
 * менеджера компонентов [ComponentManager]. Менеджер компонентов попытается найти существующий
 * или создать новый экземпляр компонента, соответствующий этим свойствам
 */
data class ComponentProperties<T : ComponentApi>(
        /**
         * Интерфейс API, который реализует компонент
         */
        val klass: KClass<T>,
)