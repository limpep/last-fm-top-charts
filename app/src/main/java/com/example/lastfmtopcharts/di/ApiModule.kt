package com.example.lastfmtopcharts.di

import android.os.Build
import com.example.lastfmtopcharts.BuildConfig
import com.example.lastfmtopcharts.model.api.LastFMAPICalls
import com.example.lastfmtopcharts.service.LastFMAPIService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class ApiModule {

    private val BASE_URL = "https://ws.audioscrobbler.com/2.0/"

    @Provides
    @Singleton
    fun provideLastFmApi(okHttpClient: OkHttpClient): LastFMAPICalls {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
            .create(LastFMAPICalls::class.java)
    }

    external fun stringFromJNI(): String

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            //  .cache(cache)
            .addInterceptor {
                    chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader(
                        "User-Agent",
                        "TopChartsApp" + " BUILD VERSION: " + BuildConfig.VERSION_NAME + " SMARTPHONE: " + Build.MODEL + " ANDROID VERSION: " + Build.VERSION.RELEASE
                    )
                    .addHeader("Content-Type", "application/json")

                val httpUrl = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", stringFromJNI())
                    .addQueryParameter("format", "json").build()

                val request = requestBuilder
                    .url(httpUrl)
                    .build()

                chain.proceed(request)
            }
            .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        client.addNetworkInterceptor(interceptor)
        return client.build()
    }

    @Singleton
    @Provides
    open fun provideLastFMAPIService(): LastFMAPIService {
        return LastFMAPIService()
    }
}