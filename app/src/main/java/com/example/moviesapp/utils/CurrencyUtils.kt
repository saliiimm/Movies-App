package com.example.moviesapp.utils


import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun formatCurrency(value: Int): String {
    val dec = DecimalFormat("###,###,###,###,###.00", DecimalFormatSymbols(Locale.ENGLISH))
    return dec.format(value).replace(",", " ")
}
