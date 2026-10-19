package com.assessment.sereneecommerce.data.repository

import com.assessment.sereneecommerce.data.local.ProductDao
import com.assessment.sereneecommerce.data.local.ProductEntity
import com.assessment.sereneecommerce.data.model.Product
import com.assessment.sereneecommerce.data.remote.ProductApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ProductRepository(
    private val apiService: ProductApiService,
    private val productDao: ProductDao
) {
    suspend fun getProducts(): Result<List<Product>> {
        return try {
            if (Random.nextBoolean()) throw Exception("Random network error occurred!")
            withContext(Dispatchers.IO) {
                val response = apiService.getProducts()
                productDao.insertAll(response.map { ProductEntity.fromProduct(it) })
                Result.success(response)
            }
        } catch (e: Exception) {
            val cached = productDao.getAllProducts().first().map { it.toProduct() }
            if (cached.isNotEmpty()) Result.success(cached) else Result.failure(e)
        }
    }
}