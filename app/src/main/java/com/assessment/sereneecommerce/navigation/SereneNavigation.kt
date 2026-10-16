package com.assessment.sereneecommerce.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null
) {
    object Home : Screen(
        route = "home",
        label = "Shop",
        selectedIcon = Icons.Outlined.Storefront,
        unselectedIcon = Icons.Outlined.Home
    )

    object Cart : Screen(
        route = "cart",
        label = "Cart",
        selectedIcon = Icons.Outlined.ShoppingBag,
        unselectedIcon = Icons.Outlined.ShoppingBag
    )

    object Favorites : Screen(
        route = "favorites",
        label = "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    )

    object Profile : Screen(
        route = "profile",
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )

    object Detail : Screen(
        route = "detail/{productJson}", label = "Detail"
    ) {
        fun createRoute(productJson: String) = "detail/$productJson"
    }
}

val bottomNavItems = listOf(
    Screen.Home, Screen.Cart, Screen.Favorites, Screen.Profile
)
