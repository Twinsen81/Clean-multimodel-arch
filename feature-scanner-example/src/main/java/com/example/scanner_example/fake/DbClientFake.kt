package com.example.scanner_example.fake

import com.example.core_db_api.data.DbClient
import com.example.core_db_api.di.CoreDbApi
import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import javax.inject.Inject

internal class DbClientFake @Inject constructor() : DbClient

internal class FakeCoreDbComponentFactory : ComponentFactory<CoreDbApi> {
    override fun create(componentManager: ComponentManager): CoreDbApi {
        return object : CoreDbApi {
            override fun dbClient() = DbClientFake()
        }
    }
}