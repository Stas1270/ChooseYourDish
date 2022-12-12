package com.example.chooseyourdish.feature.meal_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.chooseyourdish.core.ui.base.MealScaffold
import com.example.chooseyourdish.core.ui.meal.MealDetailsView
import com.example.chooseyourdish.core.ui.meal.ProgressLoadingView
import com.example.chooseyourdish.feature.meal_details.viewmodel.MealDetailsUiState

@Composable
fun MealDetailsScreen(
    id: String,
    name: String,
    uiState: MealDetailsUiState,
    loadMealDetailsAction: ()-> Unit
) {
    MealScaffold(name) {
        when (uiState) {
            is MealDetailsUiState.NoData -> {
                when {
                    uiState.isLoading -> { ProgressLoadingView() }
                    uiState.errorMessages.isNotEmpty() -> {
                        Box(Modifier.fillMaxSize()) {
                            Text(text = "No data")
                        }
                    }
                    else -> { loadMealDetailsAction.invoke() }
                }
            }
            is MealDetailsUiState.HasData -> {
                val meal = uiState.data
                MealDetailsView(meal)
            }
        }
    }
}