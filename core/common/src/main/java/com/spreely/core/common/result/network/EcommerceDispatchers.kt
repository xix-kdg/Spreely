package com.spreely.core.common.result.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val ecommerceDispatcher: EcommerceDispatchers)

enum class EcommerceDispatchers {
    Default,
    IO
}
