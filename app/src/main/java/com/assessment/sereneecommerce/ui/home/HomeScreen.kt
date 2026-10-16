package com.assessment.sereneecommerce.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assessment.sereneecommerce.data.model.Product
import com.assessment.sereneecommerce.di.ServiceLocator
import com.assessment.sereneecommerce.ui.theme.CuratedBadge
import com.assessment.sereneecommerce.ui.theme.SereneButton
import com.assessment.sereneecommerce.ui.theme.SereneCategoryChip
import com.assessment.sereneecommerce.ui.theme.SereneCommerceTheme
import com.assessment.sereneecommerce.ui.theme.SereneProductCard
import com.assessment.sereneecommerce.ui.theme.SereneSearchBar
import com.assessment.sereneecommerce.ui.theme.spacing

@Composable
fun HomeScreen(
    onProductClick: (Product) -> Unit = {}
) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(ServiceLocator.productRepository)
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        state = uiState, onRetry = { viewModel.fetchProducts() }, onProductClick = onProductClick
    )
}


@Composable
fun HomeContent(
    state: HomeUiState, onRetry: () -> Unit = {}, onProductClick: (Product) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.xs)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.md)
            ) {
                Text(
                    text = "MINDFUL SELECTIONS",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Discover & Shop",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    CuratedBadge()
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
                SereneSearchBar(
                    value = searchQuery, onValueChange = { searchQuery = it })
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
        }

        when (state) {
            is HomeUiState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.xl),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ErrorOutline,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = state.message,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                        SereneButton(text = "Try Again", onClick = onRetry)
                    }
                }
            }

            HomeUiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.xl),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            is HomeUiState.Success -> {
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.md),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)
                    ) {
                        items(state.categories) { category ->
                            SereneCategoryChip(
                                text = category,
                                selected = selectedCategory == category,
                                onClick = { selectedCategory = category })
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                val filteredProducts = if (selectedCategory == "All") {
                    state.products
                } else {
                    state.products.filter { it.category == selectedCategory }
                }

                items(filteredProducts, key = { it.id }) { product ->
                    SereneProductCard(
                        title = product.name,
                        category = product.category,
                        subCategory = product.subCategory,
                        keywords = product.keywords,
                        price = "$${"%.2f".format(product.priceCents / 100.0)}",
                        rating = product.rating.stars,
                        reviewCount = product.rating.count,
                        description = product.description,
                        imageUrl = product.image,
                        onAddClick = { },
                        onClick = { onProductClick(product) },
                        modifier = Modifier.padding(
                            horizontal = MaterialTheme.spacing.md,
                            vertical = MaterialTheme.spacing.xs
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    SereneCommerceTheme {
        HomeContent(state = HomeUiState.Loading)
    }
}
