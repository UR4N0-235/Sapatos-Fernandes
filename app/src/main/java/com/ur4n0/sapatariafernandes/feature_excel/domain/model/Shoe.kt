package com.ur4n0.sapatariafernandes.feature_excel.domain.model

data class Shoe(
    val code: Long,
    val name: String,
    val color: String,
    val size: Int,
    val price: Double,
    val stock: Int,
)