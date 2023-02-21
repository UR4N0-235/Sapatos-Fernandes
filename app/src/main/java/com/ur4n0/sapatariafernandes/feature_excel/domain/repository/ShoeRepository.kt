package com.ur4n0.sapatariafernandes.feature_excel.domain.repository

import com.ur4n0.sapatariafernandes.core.util.Resource
import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe
import kotlinx.coroutines.flow.Flow

interface ShoeRepository {
    fun getAllShoes(): Flow<Resource<List<Shoe>>>
    fun getShoesByCode(code: Long): Flow<Resource<List<Shoe>>>
}