package com.assessment.sereneecommerce.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.EnergySavingsLeaf
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.assessment.sereneecommerce.navigation.bottomNavItems

// --- Buttons ---

@Composable
fun SereneButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = CircleShape, // Pill geometry
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = null
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.xs)
                    .size(20.dp)
            )
        }
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun SereneSecondaryButton(
    text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = CircleShape,
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun SereneGhostButton(
    text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun SereneQuantityPicker(
    quantity: Int, onQuantityChange: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .height(48.dp)
            .width(140.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Minus Button
            Surface(
                onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Quantity Text
            Text(
                text = quantity.toString(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Plus Button
            Surface(
                onClick = { onQuantityChange(quantity + 1) },
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// --- Chips & Badges ---

@Composable
fun SereneCategoryChip(
    text: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(32.dp),
        shape = CircleShape,
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer,
        contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun SereneStatusBadge(
    text: String, type: SereneBadgeType, modifier: Modifier = Modifier
) {
    val containerColor = when (type) {
        SereneBadgeType.NEW -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
        SereneBadgeType.SALE -> MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f)
    }
    val contentColor = when (type) {
        SereneBadgeType.NEW -> MaterialTheme.colorScheme.primary
        SereneBadgeType.SALE -> MaterialTheme.colorScheme.error
    }

    Surface(
        modifier = modifier.height(24.dp),
        shape = CircleShape,
        color = containerColor,
        contentColor = contentColor
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 10.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = MaterialTheme.typography.labelSmall)
        }
    }
}

enum class SereneBadgeType { NEW, SALE }

// --- Product Card ---

@Composable
fun SereneProductCard(
    title: String,
    category: String,
    subCategory: String,
    keywords: List<String>,
    price: String,
    rating: Float,
    reviewCount: Int,
    description: String,
    imageUrl: String?,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // Image placeholder

                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(MaterialTheme.spacing.xs),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xxs)
                ) {
                    Surface(
                        shape = CircleShape, color = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.xs,
                                vertical = MaterialTheme.spacing.xxs
                            )

                        )
                    }

                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                    ) {
                        Text(
                            text = subCategory,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.xs,
                                vertical = MaterialTheme.spacing.xxs
                            )
                        )
                    }

                }

                var favourite by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(MaterialTheme.spacing.xs)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                    ) {
                        IconButton(onClick = { favourite = !favourite }) {
                            Icon(
                                imageVector = if (favourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = if (favourite) "Remove from favourites" else "Add to favourite",
                                tint = if (favourite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                            )

                        }
                    }

                }
            }
            Spacer(Modifier.height(MaterialTheme.spacing.md))
            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.xs)
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (index < rating.toInt()) RatingAccent else MaterialTheme.colorScheme.outlineVariant
                    )
                }
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.xxs))
                Text(
                    text = rating.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.xxs))
                Text(
                    text = "($reviewCount reviews)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.weight(1F))
                Text(
                    text = price,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    keywords.forEach {
                        Text(
                            text = "#$it",
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    var quantity by remember { mutableIntStateOf(1) }
                    SereneQuantityPicker(
                        quantity = quantity, onQuantityChange = { quantity = it })
                    Spacer(Modifier.width(MaterialTheme.spacing.md))
                    SereneButton(
                        text = "Add to Cart",
                        icon = Icons.Default.ShoppingCartCheckout,
                        modifier = Modifier.weight(1f),
                        onClick = { onAddClick() })
                }
            }
        }
    }
}

// --- Inputs & Controls ---

@Composable
fun SereneTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
            )
        },
        shape = MaterialTheme.shapes.medium, // 12dp radius
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            errorContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        isError = isError,
        singleLine = singleLine,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun SereneCheckbox(
    checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier.size(22.dp),
        colors = androidx.compose.material3.CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            uncheckedColor = MaterialTheme.colorScheme.outlineVariant,
            checkmarkColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun SereneRadioButton(
    selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        modifier = modifier.size(22.dp),
        colors = androidx.compose.material3.RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = MaterialTheme.colorScheme.outlineVariant
        )
    )
}

// --- Header & Special Elements ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun SereneTopBar(
    title: String = "Shop Explore",
    shouldShowNavIcon: Boolean = false,
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onNavIconClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(), title = {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = if (shouldShowNavIcon) MaterialTheme.spacing.md else 0.dp)
        )
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background
    ), windowInsets = WindowInsets(right = MaterialTheme.spacing.xs), navigationIcon = {
        if (shouldShowNavIcon) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "back",
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.xs)
                    .size(32.dp)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .clickable {
                        onNavIconClick()
                    })
        }
    }, actions = {
        if (shouldShowNavIcon) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                modifier = Modifier
                    .size(MaterialTheme.spacing.lg)
                    .clip(CircleShape)
                    .clickable(onClick = onShareClick)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(MaterialTheme.spacing.lg)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(2.dp)
                    .clickable(onClick = onProfileClick)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .size(MaterialTheme.spacing.lg)
                    .clip(CircleShape)
                    .clickable(onClick = onSearchClick)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))

            BadgedBox(
                modifier = Modifier.size(MaterialTheme.spacing.lg), badge = {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Text("2")
                    }
                }) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingBag,
                    contentDescription = "Cart",
                    modifier = Modifier.clickable(onClick = onCartClick)
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(MaterialTheme.spacing.lg)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                    .padding(2.dp)
                    .clickable(onClick = onProfileClick)
            )
        }
    })

}

@Composable
fun SereneBottomNavBar(currentDestination: NavDestination?, navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        bottomNavItems.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                icon = {
                if (screen.selectedIcon != null && screen.unselectedIcon != null) {
                    if (selected) Icon(
                        imageVector = screen.selectedIcon, contentDescription = screen.label
                    ) else Icon(
                        imageVector = screen.unselectedIcon, contentDescription = screen.label
                    )
                }
            }, label = {
                Text(text = screen.label, style = MaterialTheme.typography.labelSmall)
            }, selected = selected, onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }, colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
            )
            )
        }
    }
}

@Composable
fun SereneSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search mindful goods, materials...",
    onFilterClick: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Filter",
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = onFilterClick),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        shape = CircleShape,
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}

@Preview
@Composable
fun CuratedBadge(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(28.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f),
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.EnergySavingsLeaf,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = RatingAccent
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Curated", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview
@Composable
fun SereneProductCardPreview() {
    SereneCommerceTheme {
        SereneProductCard(
            title = "Organic Herbal Facial Serum",
            category = "Beauty & Personal Care",
            subCategory = "Skincare",
            keywords = listOf("moisturizer", "hydration", "skincare", "beauty"),
            price = "$${2000 / 100.0}",
            rating = 4.5f,
            reviewCount = 120,
            description = "This Hydrating Facial Moisturizer is expertly formulated to deeply nourish and hydrate your skin, providing lasting moisture and a smooth, radiant complexion. Ideal for daily use.",
            imageUrl = "https://kolzsticks.github.io/Free-Ecommerce-Products-Api/main/images/products/moisturizer.jpg",
            onAddClick = {})
    }
}
