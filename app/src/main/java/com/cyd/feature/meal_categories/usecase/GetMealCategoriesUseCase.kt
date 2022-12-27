package com.cyd.feature.meal_categories.usecase

import com.cyd.core.data.model.mealdb.Category
import com.cyd.core.data.usecase.UseCaseSuspend
import com.cyd.data.categories.CategoriesRepository
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) : UseCaseSuspend<Nothing?, List<Category>> {

    override suspend fun execute(params: Nothing?): List<Category> {
        return repository.getMealCategories()?.map {
            Category(
                id = it.id.orEmpty(),
                name = it.name.orEmpty(),
                description = it.description.orEmpty(),
                thumb = it.imageThumb.orEmpty()
            )
        }.orEmpty()
    }
}