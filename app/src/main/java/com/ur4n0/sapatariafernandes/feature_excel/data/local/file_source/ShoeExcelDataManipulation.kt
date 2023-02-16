package com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source

import org.apache.commons.math3.exception.NotANumberException
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.util.CellReference

class ShoeExcelDataManipulation {
    private val header = ShoeExcelInfo(0,0,0,0,0,0, 0)
    private var maxValueRowNumber: Int = 0

    fun getShoes(workbook: Workbook):List<ShoeExcelData> {
        var shoesListToReturn: ArrayList<ShoeExcelData> = ArrayList()
        val sheet = workbook.getSheetAt(0)

        if(header.headerRow + maxValueRowNumber == 0) defineHeaderAndMaxNowEmptyRow(sheet)

        for(index in header.headerRow..maxValueRowNumber ){
            val row = sheet.getRow(index)
            try{
                shoesListToReturn.add(ShoeExcelData(
                    row.getCell(header.codeColumn).numericCellValue.toLong(),
                    row.getCell(header.colorColumn).stringCellValue,
                    row.getCell(header.nameColumn).stringCellValue,
                    row.getCell(header.sizeColumn).numericCellValue.toInt(),
                    row.getCell(header.priceColumn).numericCellValue,
                    row.getCell(header.stockColumn).numericCellValue.toInt(),
                ))
            }catch(err: NotANumberException){
                continue
            }
        }
        return shoesListToReturn.toList()
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

    private fun defineWhereIsHeaderRow(){

    }
}