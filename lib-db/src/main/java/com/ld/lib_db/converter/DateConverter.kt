package com.ld.lib_db.converter

import android.provider.ContactsContract
import android.util.Log
import androidx.room.TypeConverter
import java.util.*

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
class DateConverter {
    @TypeConverter
    fun revertDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun converterDate(value: Date?): Long {
        var value = value
        if (value == null) {
            value = Date()
        }
        Log.i("DateConverter", "converterDate=" + value.time + "")
        return value.time
    }
}