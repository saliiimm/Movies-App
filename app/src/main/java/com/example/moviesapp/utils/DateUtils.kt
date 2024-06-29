package com.example.moviesapp.utils


import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatYearFromDate(dateString: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val releaseDate = sdf.parse(dateString)
    val calendar = Calendar.getInstance()
    calendar.time = releaseDate ?: Date()
    return calendar.get(Calendar.YEAR).toString()
}
