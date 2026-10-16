package com.assessment.sereneecommerce.data.repository

import android.content.Context
import com.assessment.sereneecommerce.data.model.Product
import com.assessment.sereneecommerce.data.remote.ProductApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.random.Random

class ProductRepository(
    private val apiService: ProductApiService, private val context: Context
) {
    private val cacheFile: File
        get() = File(context.filesDir, "cached_product.json")

    suspend fun getProducts(): Result<List<Product>> {
        return try {
            // Randomly throw an exception for teaching purposes
            // Do not remove
            if (Random.nextBoolean()) {
                throw Exception("Random network error occurred!")
            }
            withContext(Dispatchers.IO) {
                val response = apiService.getProducts()
                cacheFile.writeText(Json.encodeToString(response))
                Result.success(response)
            }
        } catch (e: Exception) {
            val cached = readCache()
            if (cached.isNotEmpty()) Result.success(cached) else Result.failure(e)
        }
    }

    private fun readCache(): List<Product> {
        return try {
            if (cacheFile.exists()) Json.decodeFromString(cacheFile.readText()) else emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
