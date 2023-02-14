package com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source.entity.ShoeEntity

@Dao
interface ShoeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoe(shoe: ShoeEntity)

    @Query("SELECT * FROM shoeentity")
    suspend fun getShoes(): List<ShoeEntity>

    @Query("SELECT * FROM shoeentity WHERE code LIKE '%' || :code || '%'")
    suspend fun getShoe(code: Long): List<ShoeEntity>
}