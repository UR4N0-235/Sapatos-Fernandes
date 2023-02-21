package com.ur4n0.sapatariafernandes.feature_excel.domain.use_case

import com.ur4n0.sapatariafernandes.core.util.Resource
import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe
import com.ur4n0.sapatariafernandes.feature_excel.domain.repository.ShoeRepository
import kotlinx.coroutines.flow.Flow

class GetShoesByCode (
    private val repository: ShoeRepository
    ) {
        operator fun invoke(code: Long): Flow<Resource<List<Shoe>>> {
            return repository.getShoesByCode(code)
        }
}