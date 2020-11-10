package com.example.core_network_api.di

import com.example.core_network_api.data.HttpClient
import com.example.module_injector.ComponentApi

interface CoreNetworkApi : ComponentApi {
    fun httpClient(): HttpClient
}