package com.bbb.hades.core.common.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: HadesDispatcher)

enum class HadesDispatcher {
    Default,
    IO,
}