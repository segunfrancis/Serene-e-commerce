package com.assessment.sereneecommerce.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.assessment.sereneecommerce.data.model.Product
import com.assessment.sereneecommerce.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val products: List<Product>, val categories: List<String>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            repository.getProducts().onSuccess { products ->
                    Log.d("HomeViewModel", "Successfully fetched $products")
                    val categories = products.map { it.category }.distinct().toMutableList()
                    categories.add(0, "All")
                    _uiState.value = HomeUiState.Success(products, categories)
                }.onFailure { exception ->
                    Log.e("HomeViewModel", "Error fetching products", exception)
                    _uiState.value = HomeUiState.Error(exception.message ?: "Unknown error")
                }
        }
    }
}

class HomeViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
