package com.kyodude.todo_mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    private var title: String?,
    private var description: String?,
    private var priority: Int
) {
    fun getTitle(): String? {
       return title
    }

    fun getDescription(): String? {
        return description
    }

    fun getPriority(): Int {
        return priority
    }

}