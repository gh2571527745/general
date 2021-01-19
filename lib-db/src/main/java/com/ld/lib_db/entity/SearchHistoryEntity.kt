package com.ld.lib_db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

/**
 *  author : ld
 *  time   : 2021/01/19
 *  desc   :（实体）
 */
@Entity(indices = [Index(value = ["name"], unique = true)])
class SearchHistoryEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @NonNull
    var name: String

    @NonNull
    var insertTime: Date

    constructor(name: String, insertTime: Date) {
        this.name = name
        this.insertTime = insertTime
    }
}