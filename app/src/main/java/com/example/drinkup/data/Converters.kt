package com.example.drinkup.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let {
            try {
                LocalDateTime.parse(it, dateTimeFormatter)
            } catch (e: Exception) {
                null
            }
        }
    }

    @TypeConverter
    fun dateTimeToTimestamp(date: LocalDateTime?): String? {
        return date?.format(dateTimeFormatter)
    }

    @TypeConverter
    fun fromDateString(value: String?): LocalDate? {
        return value?.let {
            try {
                LocalDate.parse(it, dateFormatter)
            } catch (e: Exception) {
                null
            }
        }
    }

    @TypeConverter
    fun dateToString(date: LocalDate?): String? {
        return date?.format(dateFormatter)
    }
}