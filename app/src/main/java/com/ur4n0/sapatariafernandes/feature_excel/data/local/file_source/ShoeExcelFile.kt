package com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

private const val EXCEL_FILE_NAME = "sapatos.xlsx"

abstract class ShoeExcelFile (context: Context) {
    var context: Context? = context

    fun GetExcelFile(): Workbook{
        if(context == null) return throw NullPointerException()

        val workbookFile = File(context!!.filesDir, EXCEL_FILE_NAME);
        return XSSFWorkbook(workbookFile);
    }

    fun SaveExcelFile(workbook: Workbook): Boolean{
        return try {
            val fileOutput = File(context!!.filesDir, EXCEL_FILE_NAME)
            workbook.write(FileOutputStream(fileOutput))
            true
        }catch (err: IOException){
            false
        }catch(err: NullPointerException){
            false
        }
    }


}