package com.example.zhanylandroid.di.modules

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.example.zhanylandroid.data.network.BreakingBadApi
import com.example.zhanylandroid.di.annotations.HttpLoggerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val isDebug get() = BuildConfig.DEBUG

    @Provides
    @HttpLoggerInterceptor
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .apply {
                val interceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                if(isDebug) addInterceptor(interceptor)
            }
            .build()
    }

    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun retrofit(
        @HttpLoggerInterceptor
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson()))
            .build()
    }

    @Provides
    fun breakingBadApi(retrofit: Retrofit): BreakingBadApi {
        return retrofit.create(BreakingBadApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://breakingbadapi.com/api/"
        const val TIMEOUT = 300L
    }
}