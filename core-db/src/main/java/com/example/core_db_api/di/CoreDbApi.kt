package com.example.core_db_api.di

import com.example.core_db_api.data.DbClient
import com.example.module_injector.ComponentApi

interface CoreDbApi : ComponentApi {
    fun dbClient(): DbClient
}