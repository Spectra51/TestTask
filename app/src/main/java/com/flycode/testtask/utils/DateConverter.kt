package com.flycode.testtask.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateConverter {
        private const val DATE_FORMAT = "yyyy-MM-dd"

        fun stringToDate(dateString: String): Date? {
            val dateFormat = SimpleDateFormat(DATE_FORMAT)
            try {
                return dateFormat.parse(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
                return null
            }
        }

        fun dateToString(date: Date): String {
            val dateFormat = SimpleDateFormat(DATE_FORMAT)
            return dateFormat.format(date)
        }

    fun formatDateToShortMonth(date: Date): String {
        val dateFormat = SimpleDateFormat("dd MMM", Locale("ru", "RU"))
        return dateFormat.format(date)
    }
    fun formatDateToHotel(date: Date): String {
        val dayFormat = SimpleDateFormat("dd", Locale("ru", "RU"))
        val monthFormat = SimpleDateFormat("MM", Locale("ru", "RU"))
        val dayOfWeekFormat = SimpleDateFormat("EEE", Locale("ru", "RU"))

        val day = dayFormat.format(date)
        val month = monthFormat.format(date)
        val dayOfWeek = dayOfWeekFormat.format(date)

        return "$day.$month $dayOfWeek"
    }
}