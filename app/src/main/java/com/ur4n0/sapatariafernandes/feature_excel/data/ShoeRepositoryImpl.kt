package com.ur4n0.sapatariafernandes.feature_excel.data

import com.ur4n0.sapatariafernandes.core.util.Resource
import com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source.ShoeDAO
import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe
import com.ur4n0.sapatariafernandes.feature_excel.domain.repository.ShoeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShoeRepositoryImpl(
    private val shoeDao: ShoeDAO,
): ShoeRepository {
    override fun getShoes(code: Long?): Flow<Resource<List<Shoe>>> = flow {
            emit(Resource.Loading())

            val shoes = if ( code == null ) shoeDao.getShoes().map { it.toShoe() }
            else shoeDao.getShoe(code).map{ it.toShoe()}

            emit(Resource.Loading(data = shoes))

            // TODO("code to update database list, getting all shoes from excel file")

            val newShoes = if ( code == null ) shoeDao.getShoes().map { it.toShoe() }
            else shoeDao.getShoe(code).map{ it.toShoe()}

            emit(Resource.Success(newShoes))
    }
}