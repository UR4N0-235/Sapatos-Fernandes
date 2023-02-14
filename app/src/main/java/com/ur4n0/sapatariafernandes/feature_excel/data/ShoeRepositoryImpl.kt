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
    override fun getShoes(): Flow<Resource<List<Shoe>>> = flow {
        emit(Resource.Loading())

        val shoes = shoeDao.getShoes().map { it.toShoe() }
        emit(Resource.Loading(data = shoes))

        // TODO("code to update database list, getting all shoes from excel file")

        val newShoes = shoeDao.getShoes().map { it.toShoe() }
        emit(Resource.Success(newShoes))
    }

    override fun getShoe(code: Long): Flow<Resource<List<Shoe>>> = flow {
        emit(Resource.Loading())

        val shoe = shoeDao.getShoe(code).map { it.toShoe() }
        emit(Resource.Loading(data = shoe))

        // TODO("code to update database list, getting all shoes from excel file")

        val newShoe = shoeDao.getShoe(code).map { it.toShoe() }
        emit(Resource.Success(newShoe))
    }
}