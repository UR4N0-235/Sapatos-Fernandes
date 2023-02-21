package com.ur4n0.sapatariafernandes.feature_excel.data

import com.ur4n0.sapatariafernandes.core.util.Resource
import com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source.ShoeDAO
import com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source.ShoeExcelDataManipulation
import com.ur4n0.sapatariafernandes.feature_excel.domain.model.Shoe
import com.ur4n0.sapatariafernandes.feature_excel.domain.repository.ShoeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShoeRepositoryImpl(
    private val shoeDao: ShoeDAO,
    private val shoeExcel: ShoeExcelDataManipulation
) : ShoeRepository {
    override fun getAllShoes(): Flow<Resource<List<Shoe>>> = flow {
        emit(Resource.Loading())

        val databaseShoes = shoeDao.getShoes().map { it.toShoe() }

        emit(Resource.Loading(data = databaseShoes))

        try {
            val excelShoes = shoeExcel.getShoes().map { it.toShoeEntity() }
            shoeDao.deleteShoe(excelShoes.map { it.code })
            shoeDao.insertShoe(excelShoes.map { it })
        } catch (err: NullPointerException) {
            emit(
                Resource.Error(
                    message = "Algo deu errado...",
                    data = databaseShoes
                )
            )
        } catch (err: Exception) {
            emit(
                Resource.Error(
                    message = "Erro desconhecido",
                    data = databaseShoes
                )
            )
        }

        val newShoes = shoeDao.getShoes().map { it.toShoe() }

        emit(Resource.Success(newShoes))
    }

    override fun getShoesByCode(code: Long): Flow<Resource<List<Shoe>>> = flow {
        emit(Resource.Loading())

        val databaseShoes = shoeDao.getShoe(code).map { it.toShoe() }

        emit(Resource.Loading(data = databaseShoes))

        try {
            val excelShoes = shoeExcel.getShoes().map { it.toShoeEntity() }
            shoeDao.deleteShoe(excelShoes.map { it.code })
            shoeDao.insertShoe(excelShoes.map { it })
        } catch (err: NullPointerException) {
            emit(
                Resource.Error(
                    message = "Algo deu errado...",
                    data = databaseShoes
                )
            )
        } catch (err: Exception) {
            emit(
                Resource.Error(
                    message = "Erro desconhecido",
                    data = databaseShoes
                )
            )
        }

        val newShoes = shoeDao.getShoe(code).map { it.toShoe() }

        emit(Resource.Success(newShoes))

    }
}