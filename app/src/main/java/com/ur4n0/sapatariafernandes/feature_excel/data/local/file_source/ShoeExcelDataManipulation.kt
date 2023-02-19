package com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source

import android.content.Context
import com.ur4n0.sapatariafernandes.R
import com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source.entity.ShoeExcelData
import org.apache.commons.math3.exception.NotANumberException
import org.apache.poi.openxml4j.exceptions.InvalidFormatException
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

private const val EXCEL_FILE_NAME = "sapatos.xlsx"

class ShoeExcelDataManipulation(context: Context) {
    private var context: Context? = context
    private val header = ShoeExcelInfo(0,0,0,0,0,0, 0)
    private var maxValueRowNumber: Int = 0

    companion object{

    }

    fun getShoes():List<ShoeExcelData> {
        val workbook: Workbook = GetExcelFile()
        var shoesListToReturn: ArrayList<ShoeExcelData> = ArrayList()
        val sheet = workbook.getSheetAt(0)

        if(header.headerRow + maxValueRowNumber == 0) defineHeaderAndMaxNowEmptyRow(sheet)

        for(index in header.headerRow..maxValueRowNumber ){
            val row = sheet.getRow(index)
            try{
                shoesListToReturn.add(
                    ShoeExcelData(
                    row.getCell(header.codeColumn).numericCellValue.toLong(),
                    row.getCell(header.colorColumn).stringCellValue,
                    row.getCell(header.nameColumn).stringCellValue,
                    row.getCell(header.sizeColumn).numericCellValue.toInt(),
                    row.getCell(header.priceColumn).numericCellValue,
                    row.getCell(header.stockColumn).numericCellValue.toInt(),
                )
                )
            }catch(err: NotANumberException){
                continue
            }
        }
        return shoesListToReturn.toList()
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

    private fun defineHeaderAndMaxNowEmptyRow(sheet: Sheet){
        for(row:Row in sheet){
            if(row == null) continue
            else maxValueRowNumber++

            for(cell:Cell in row){
                val cellReference = CellReference(row.rowNum, cell.columnIndex)
                if(header.headerRow != 0) break

                if(cellReference.formatAsString() == "codigo") {
                    header.codeColumn = cell.columnIndex
                    header.colorColumn = cell.columnIndex + 1
                    header.nameColumn = cell.columnIndex + 2
                    header.sizeColumn = cell.columnIndex + 3
                    header.priceColumn = cell.columnIndex + 4
                    header.stockColumn = cell.columnIndex + 5
                    header.headerRow = row.rowNum
                }
            }
        }
    }

    private fun GetExcelFile(): Workbook{
        if(context == null) return throw NullPointerException()
//        var workbook: File;

        try{
           val workbookFile = OPCPackage.open(
                File(context!!.filesDir, EXCEL_FILE_NAME)
            );
            return XSSFWorkbook(workbookFile);
        }catch(err: InvalidFormatException){
            installExcelFileOnInternalStorage()

            val workbookFile = OPCPackage.open(
                File(context!!.filesDir, EXCEL_FILE_NAME)
            );
            return XSSFWorkbook(workbookFile);
        }
    }

    private fun installExcelFileOnInternalStorage(){
        val excelFile = context!!.resources.openRawResource(
            context!!.resources.getIdentifier("sapatos",
                "raw", context!!.packageName
            ));
        val writeTo = FileOutputStream(File(context!!.filesDir, EXCEL_FILE_NAME))
        excelFile.copyTo(writeTo)

        excelFile.close()
        writeTo.close()
    }
}