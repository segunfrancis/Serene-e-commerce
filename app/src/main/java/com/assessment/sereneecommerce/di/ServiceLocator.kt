package com.assessment.sereneecommerce.di

import android.content.Context
import com.assessment.sereneecommerce.data.remote.ProductApiService
import com.assessment.sereneecommerce.data.repository.ProductRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object ServiceLocator {
    private const val BASE_URL = "https://kolzsticks.github.io/Free-Ecommerce-Products-Api/main/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()

    private val apiService: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }

    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    val productRepository: ProductRepository by lazy {
        ProductRepository(apiService, appContext)
    }
}
