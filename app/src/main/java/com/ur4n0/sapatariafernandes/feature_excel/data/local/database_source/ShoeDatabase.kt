package com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source.entity.ShoeEntity

@Database(
    entities = [ShoeEntity::class],
    version = 1
)
abstract class ShoeDatabase: RoomDatabase() {
    abstract val dao: ShoeDAO
}
