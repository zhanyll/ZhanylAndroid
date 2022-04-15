package com.example.zhanylandroid.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpLoggerInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpWithoutLoggingInterceptor