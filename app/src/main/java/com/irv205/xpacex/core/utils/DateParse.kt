package com.irv205.xpacex.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.withFormat(format : String) : String{
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(this)
}

fun parseDate(date: Date): String{
    val sdf = SimpleDateFormat("MMMM dd, yyyy",Locale.getDefault()).format(date)
    return sdf.toString()
}