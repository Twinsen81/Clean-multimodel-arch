package com.example.scanner_api

import com.example.module_injector.ComponentApi


interface ScannerFeatureApi : ComponentApi {
    fun scannerStarter(): ScannerStarter
}