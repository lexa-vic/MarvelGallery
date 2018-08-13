package com.kostikov.marvelgallery.data.network

import com.google.gson.Gson
import com.kostikov.marvelgallery.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Kostikov Aleksey.
 */

val retrofit by lazy { makeRetrofit() }

fun makeRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/v1/public/")
        .client(makeHttpClient())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


fun makeHttpClient() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(makeHeadersInterceptor())
        .addInterceptor(makeLoggingInterceptor())
        .addInterceptor(makeAddSecurityQueryInterceptor())
        .build()


fun makeHeadersInterceptor() = Interceptor{ chain ->
    chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build())
}

fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
}

fun makeAddSecurityQueryInterceptor() = Interceptor { chain ->
    val originalRequest = chain.request()
    val timeStamp = System.currentTimeMillis()
// Url customization: add query parameters
    val url = originalRequest.url().newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY) // 1
            .addQueryParameter("ts", "$timeStamp") // 1
            .addQueryParameter("hash", calculatedMd5(timeStamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY))
            .build()
// Request customization: set custom url
    val request = originalRequest
            .newBuilder()
            .url(url)
            .build()
    chain.proceed(request)
}