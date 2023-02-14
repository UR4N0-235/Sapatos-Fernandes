package com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe

@Entity
data class ShoeEntity(
    val code: Long,
    val name: String,
    val color: String,
    val size: Int,
    val price: Double,
    val stock: Int,
    @PrimaryKey val id: Int? = null
){
    fun toShoe(): Shoe{
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