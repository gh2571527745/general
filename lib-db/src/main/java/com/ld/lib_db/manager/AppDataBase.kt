package com.ld.lib_db.manager

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ld.lib_db.converter.DateConverter
import com.ld.lib_db.dao.SearchHistoryDao
import com.ld.lib_db.entity.SearchHistoryEntity

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :数据库持有者
 */
@TypeConverters(value = [DateConverter::class])
@Database(entities = [SearchHistoryEntity::class] , version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}