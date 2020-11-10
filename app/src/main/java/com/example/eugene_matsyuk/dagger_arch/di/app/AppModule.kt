package com.example.eugene_matsyuk.dagger_arch.di.app

import android.content.Context
import com.example.eugene_matsyuk.dagger_arch.DaggerArchApplication
import com.example.module_injector.ComponentManager
import com.example.module_injector.RootComponentManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return DaggerArchApplication.appContext
    }

    @Singleton
    @Provides
    fun provideComponentManager(): ComponentManager = RootComponentManager
}