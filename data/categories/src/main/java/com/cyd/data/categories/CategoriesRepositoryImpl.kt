package com.cyd.data.categories

import com.cyd.core.network.MealDataSource
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource
) : CategoriesRepository {

    override suspend fun getMealCategories() = mealDataSource.getMealCategories()
}