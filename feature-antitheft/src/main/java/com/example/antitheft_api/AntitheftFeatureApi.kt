package com.example.antitheft_api

import com.example.module_injector.ComponentApi


interface AntitheftFeatureApi : ComponentApi {
    fun antitheftStarter(): AntitheftStarter
}