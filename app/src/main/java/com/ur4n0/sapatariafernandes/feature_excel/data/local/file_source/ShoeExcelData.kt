package com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source

import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe

data class ShoeExcelData(
    val code: Long,
    val name: String,
    val color: String,
    val size: Int,
    val price: Double,
    val stock: Int,
){
    fun toShoe(): Shoe {
        return Shoe(
            code = code,
            name = name,
            color = color,
            size = size,
            price = price,
            stock = stock
        )
    }
}