package com.example.scanner_example.fake

import com.example.core_network_api.data.HttpClient
import com.example.core_network_api.di.CoreNetworkApi
import com.example.module_injector.ComponentFactory
import com.example.module_injector.ComponentManager
import io.reactivex.Single
import javax.inject.Inject

internal class HttpClientFake @Inject constructor() : HttpClient {
    override fun doAnyRequest(): Single<Any> {
        return Single.just(Any())
    }
}

internal class FakeCoreNetworkComponentFactory : ComponentFactory<CoreNetworkApi> {
    override fun create(componentManager: ComponentManager): CoreNetworkApi {
        return object : CoreNetworkApi {
            override fun httpClient() = HttpClientFake()
        }
    }
}