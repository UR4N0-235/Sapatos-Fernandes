package com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source.entity

import com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source.entity.ShoeEntity

data class ShoeExcelData(
    val code: Long,
    val name: String,
    val color: String,
    val size: Int,
    val price: Double,
    val stock: Int,
){
    fun toShoeEntity(): ShoeEntity {
        return ShoeEntity(
            code = code,
            name = name,
            color = color,
            size = size,
            price = price,
            stock = stock
        )
    }
}