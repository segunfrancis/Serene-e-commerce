package com.assessment.sereneecommerce.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.assessment.sereneecommerce.data.model.Product
import com.assessment.sereneecommerce.data.model.Rating
import com.assessment.sereneecommerce.ui.theme.RatingAccent
import com.assessment.sereneecommerce.ui.theme.SereneButton
import com.assessment.sereneecommerce.ui.theme.SereneCommerceTheme
import com.assessment.sereneecommerce.ui.theme.SereneQuantityPicker
import com.assessment.sereneecommerce.ui.theme.spacing

@Composable
fun DetailScreen(product: Product) {
    DetailContent(product = product)
}

@Composable
fun DetailContent(product: Product) {
    var isFavorite by remember { mutableStateOf(false) }
    var quantity by remember { mutableIntStateOf(1) }
    val currentPrice = product.priceCents / 100.0

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 88.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )

                Surface(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(MaterialTheme.spacing.sm),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.9f)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(5.dp)

                    ){
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = null,
                            modifier = Modifier
                                .size(10.dp)
                        )
                        Text(
                            text = "MINDFUL CRAFT",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.xs,
                                vertical = MaterialTheme.spacing.xxs
                            )
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(MaterialTheme.spacing.xs)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                    ) {
                        IconButton(onClick = { isFavorite = !isFavorite }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                                tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.md),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        product.category,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                                shape = MaterialTheme.shapes.large
                            ),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(
                        modifier = Modifier.width(5.dp)
                    )
                    Text(
                        "  •  ",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        product.subCategory,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                                shape = MaterialTheme.shapes.medium
                            ),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    product.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = RatingAccent,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(MaterialTheme.spacing.xxs))
                        Text(
                            product.rating.stars.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(Modifier.width(MaterialTheme.spacing.xxs))
                        Text(
                            "(${product.rating.count} customer ratings)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { }) {
                        Text(
                            "Read all reviews",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                val compareAtPrice = currentPrice * 1.3
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "$${"%.2f".format(currentPrice)}",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(MaterialTheme.spacing.xs))
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.xs,
                                vertical = MaterialTheme.spacing.xxs
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Circle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(8.dp)
                            )
                            Spacer(
                                modifier = Modifier.width(MaterialTheme.spacing.xxs)
                            )

                            Text(
                                "In Stock",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(
                                    horizontal = MaterialTheme.spacing.xs,
                                    vertical = MaterialTheme.spacing.xxs
                                )
                            )
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    Text(
                        "$${"%.2f".format(compareAtPrice)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                Spacer(Modifier.height(MaterialTheme.spacing.sm))
                Text(
                    "OVERVIEW",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(product.description, style = MaterialTheme.typography.bodyMedium)

                Spacer(Modifier.height(MaterialTheme.spacing.sm))

                Surface(
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(MaterialTheme.spacing.md),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.EnergySavingsLeaf,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(
                                Modifier.width(MaterialTheme.spacing.xs)
                            )

                            Text(
                                "Product Specifications",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Row(
                            modifier = Modifier.height(IntrinsicSize.Max),
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)
                        ) {
                            SpecChip(
                                label = "Category",
                                value = product.category,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            )
                            SpecChip(
                                label = "Subcategory",
                                value = product.subCategory,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            )
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)) {
                            SpecChip(
                                label = "Rating",
                                value = "${product.rating.stars} / 5.0",
                                modifier = Modifier.weight(1f)
                            )
                            SpecChip(
                                label = "Reviews",
                                value = "${product.rating.count}",
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(MaterialTheme.spacing.sm))
                Text(
                    "Related Themes",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    product.keywords.forEach { keyword ->
                        Text(
                            "#$keyword",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.surfaceDim,
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(
                                    vertical = MaterialTheme.spacing.xxs,
                                    horizontal = MaterialTheme.spacing.xs
                                )
                        )
                        Spacer(Modifier.width(MaterialTheme.spacing.xs))
                    }
                }

                Spacer(Modifier.height(MaterialTheme.spacing.md))

                Surface(
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.md)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "Select Quantity",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                "Free shipping on orders over $40",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        SereneQuantityPicker(
                            quantity = quantity, onQuantityChange = { quantity = it })
                    }
                }

                Spacer(Modifier.height(MaterialTheme.spacing.sm))

                Surface(
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(MaterialTheme.spacing.md),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.EnergySavingsLeaf,
                            contentDescription = null,
                        )
                        Spacer(Modifier.width(MaterialTheme.spacing.sm))
                        Column {
                            Text(
                                "Zero Carbon Footprint",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "Packaged in 100% biodegradable kraft packaging.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Fixed bottom action bar — stays on screen regardless of scroll position
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.background
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.md),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)
            ) {
                SereneButton(
                    text = "Add to Cart • $${"%.2f".format(currentPrice)}",
                    icon = Icons.Outlined.ShoppingBag,
                    modifier = Modifier.weight(2f),
                    onClick = { })
                SereneButton(
                    text = "Buy Now",
                    modifier = Modifier.weight(1f), onClick = { })
            }
        }
    }
}

@Composable
private fun SpecChip(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.sm)) {
            Text(
                label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES)
@Composable
fun DetailPreview() {
    SereneCommerceTheme {
        DetailContent(
            product = Product(
                id = "1",
                image = "https://kolzsticks.github.io/Free-Ecommerce-Products-Api/main/images/products/moisturizer.jpg",
                name = "Organic Herbal Facial Serum",
                rating = Rating(stars = 4.5f, count = 120),
                priceCents = 2000,
                category = "Beauty & Personal Care",
                subCategory = "Skincare",
                keywords = listOf("moisturizer", "hydration", "skincare", "beauty"),
                description = "This Hydrating Facial Moisturizer is expertly formulated to deeply nourish and hydrate your skin."
            )
        )
    }
}