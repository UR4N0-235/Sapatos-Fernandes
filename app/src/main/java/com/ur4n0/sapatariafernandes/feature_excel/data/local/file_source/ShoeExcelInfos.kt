package com.ur4n0.sapatariafernandes.feature_excel.data.local.file_source

import org.apache.poi.ss.usermodel.Row

data class ShoeExcelInfo(
    var headerRow: Int,
    var codeColumn: Int,
    var colorColumn: Int,
    var nameColumn: Int,
    var sizeColumn: Int,
    var priceColumn: Int,
    var stockColumn: Int
)
