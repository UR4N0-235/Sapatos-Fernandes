package com.ur4n0.sapatariafernandes.feature_excel.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ur4n0.sapatariafernandes.feature_excel.data.ShoeRepositoryImpl
import com.ur4n0.sapatariafernandes.feature_excel.data.local.database_source.ShoeDatabase
import com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source.ShoeExcelDataManipulation
import com.ur4n0.sapatariafernandes.feature_excel.domain.repository.ShoeRepository
import com.ur4n0.sapatariafernandes.feature_excel.domain.use_case.GetShoes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoeModule {
    @Provides
    @Singleton
    fun provideGetShoesUseCase(repository: ShoeRepository): GetShoes{
        return GetShoes(repository)
    }

    @Provides
    @Singleton
    fun provideShoeRepository(dao: ShoeDatabase, shoeExcel: ShoeExcelDataManipulation): ShoeRepositoryImpl{
        return ShoeRepositoryImpl(dao.dao, shoeExcel)
    }

    @Provides
    @Singleton
    fun provideShoeDatabase(app: Application): ShoeDatabase{
        return Room.databaseBuilder(
            app, ShoeDatabase::class.java, name = "shoe_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoeExcelDataManipulation(@ApplicationContext context: Context): ShoeExcelDataManipulation{
        return ShoeExcelDataManipulation(context)
    }
}