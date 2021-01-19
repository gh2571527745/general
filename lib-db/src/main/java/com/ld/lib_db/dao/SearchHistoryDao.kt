package com.ld.lib_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ld.lib_db.entity.SearchHistoryEntity

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :数据访问对象
 */
@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPerson(entity: SearchHistoryEntity?): Long

    @Query("select * from SearchHistoryEntity")
    fun selectHis(): List<SearchHistoryEntity>

    @Query("delete from SearchHistoryEntity where id=:id")
    fun deleteById(id: Long)

    @Query("delete from SearchHistoryEntity")
    fun deleteAll()
}