package com.ur4n0.sapatariafernandes.feature_excel.domain.use_case

import com.ur4n0.sapatariafernandes.core.util.Resource
import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe
import com.ur4n0.sapatariafernandes.feature_excel.domain.repository.ShoeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetShoe(
    private val repository: ShoeRepository
) {
    operator fun invoke(code: Long): Flow<Resource<List<Shoe>>>{
        if(code.toString().isBlank()){
            return flow { }
        }

        return repository.getShoe(code)
    }
}