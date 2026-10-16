package com.assessment.sereneecommerce.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThemePreview() {
    var textFieldValue by remember { mutableStateOf("") }
    var checkboxState by remember { mutableStateOf(true) }
    var selectedChip by remember { mutableStateOf("All") }
    var quantity by remember { mutableIntStateOf(1) }

    SereneCommerceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.md)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.lg)
            ) {
                Text(text = "Serene Commerce Design System", style = MaterialTheme.typography.displayLarge)

                // --- Buttons ---
                Text(text = "Buttons", style = MaterialTheme.typography.headlineMedium)
                Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)) {
                    SereneButton(text = "Primary Button", onClick = {}, modifier = Modifier.fillMaxWidth())
                    SereneSecondaryButton(text = "Secondary Button", onClick = {}, modifier = Modifier.fillMaxWidth())
                    SereneGhostButton(text = "Ghost Action", onClick = {})
                    SereneQuantityPicker(
                        quantity = quantity,
                        onQuantityChange = { quantity = it }
                    )
                }

                // --- Chips & Badges ---
                Text(text = "Chips & Badges", style = MaterialTheme.typography.headlineMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                    SereneCategoryChip(text = "All", selected = selectedChip == "All", onClick = { selectedChip = "All" })
                    SereneCategoryChip(text = "Home", selected = selectedChip == "Home", onClick = { selectedChip = "Home" })
                    SereneCategoryChip(text = "Decor", selected = selectedChip == "Decor", onClick = { selectedChip = "Decor" })
                }
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                    SereneStatusBadge(text = "NEW ARRIVAL", type = SereneBadgeType.NEW)
                    SereneStatusBadge(text = "15% OFF", type = SereneBadgeType.SALE)
                }

                // --- Inputs & Controls ---
                Text(text = "Inputs & Controls", style = MaterialTheme.typography.headlineMedium)
                SereneTextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    placeholder = "Search items..."
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
                ) {
                    SereneCheckbox(checked = checkboxState, onCheckedChange = { checkboxState = it })
                    Text(text = "Accept Terms", style = MaterialTheme.typography.bodyMedium)
                    SereneRadioButton(selected = !checkboxState, onClick = { checkboxState = false })
                    Text(text = "Option B", style = MaterialTheme.typography.bodyMedium)
                }

                // --- Product Card ---
                Text(text = "Product Card", style = MaterialTheme.typography.headlineMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)) {
                    SereneProductCard(
                        title = "Organic Linen Cushion",
                        category = "Home Decor",
                        subCategory = "Skincare",
                        keywords = listOf("moisturizer", "hydration", "skincare", "beauty"),
                        price = "$45.00",
                        rating = 4.5f,
                        reviewCount = 124,
                        description = "This Hydrating Facial Moisturizer is expertly formulated to deeply nourish and hydrate your skin, providing lasting moisture and a smooth, radiant complexion. Ideal for daily use.",
                        imageUrl = null,
                        onAddClick = {}
                    )
                }

                // --- Color Palette ---
                Text(text = "Color Palette", style = MaterialTheme.typography.headlineMedium)
                Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                        ColorBox("Primary", MaterialTheme.colorScheme.primary)
                        ColorBox("P-Cont", MaterialTheme.colorScheme.primaryContainer)
                        ColorBox("Secondary", MaterialTheme.colorScheme.secondary)
                        ColorBox("S-Cont", MaterialTheme.colorScheme.secondaryContainer)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
                        ColorBox("Surface", MaterialTheme.colorScheme.surface)
                        ColorBox("Low", MaterialTheme.colorScheme.surfaceContainerLow)
                        ColorBox("High", MaterialTheme.colorScheme.surfaceContainerHigh)
                        ColorBox("Accent", RatingAccent)
                    }
                }
            }
        }
    }
}

@Composable
fun ColorBox(name: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(color)
                .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f), MaterialTheme.shapes.medium)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, widthDp = 360)
@Preview(showBackground = true, widthDp = 360, uiMode = AndroidUiModes.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun SereneCommerceThemePreview() {
    ThemePreview()
}
