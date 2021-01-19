package com.ld.lib_db.manager

import android.content.Context
import android.text.TextUtils
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.lang.NullPointerException

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :
 */
class DbUtil private constructor() {
    private var appDataBase: AppDataBase? = null
    private lateinit var dbName: String
    private lateinit var context: Context

    companion object {
        val instance: DbUtil by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            DbUtil()
        }


        /**
         * 数据库版本 2->3 user表格新增了age列
         */
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE SearchHistoryEntity ADD COLUMN age integer");
            }
        }

        /**
         * 数据库版本 1->2 新增book表格
         */
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `book` (`bookId` INTEGER PRIMARY KEY autoincrement, `bookName` TEXT , `user_id` INTEGER, 'time' INTEGER)"
                )
            }
        }
    }

    fun init(context: Context, dbName: String) {
        this.context = context
        this.dbName = dbName
        appDataBase = null
    }

    fun getAppDataBase(): AppDataBase {
        if (appDataBase == null) {
            if (TextUtils.isEmpty(dbName)) {
                throw NullPointerException("dbName is null")
            }
            appDataBase = Room.databaseBuilder(context, AppDataBase::class.java, dbName)
                .allowMainThreadQueries()
                .enableMultiInstanceInvalidation()
                .build()
        }
        return appDataBase!!
    }
}