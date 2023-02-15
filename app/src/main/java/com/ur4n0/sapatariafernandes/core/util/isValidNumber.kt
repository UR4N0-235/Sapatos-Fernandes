package com.ur4n0.sapatariafernandes.core.util

fun isValidLong(value: String): Boolean{
    return try{
        value.toLong()
        true
    }catch(Error: NumberFormatException){
        false
    }
}