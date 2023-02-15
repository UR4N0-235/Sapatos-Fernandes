package com.ur4n0.sapatariafernandes.feature_excel.presentation

import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe

data class ShoeState(
    val shoeItems: List<Shoe> = emptyList(),
    val isLoading: Boolean = false,
)