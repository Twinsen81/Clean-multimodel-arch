package com.example.core.di.app

import com.example.core.utils.SomeUtils
import com.example.module_injector.ComponentApi

interface CoreUtilsApi : ComponentApi {
    fun someUtils(): SomeUtils
}