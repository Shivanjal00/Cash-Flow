package com.example.cashflow

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun formatDateToHumanReadableForm(dateInMillis : Long) : String{
        val dateFormatter = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }

    fun formatToDecimallValue(d : Double) : String{
        return String.format("%.2f",d)
    }

}