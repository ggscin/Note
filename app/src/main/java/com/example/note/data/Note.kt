package com.example.note.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val nameOfNote: String,
    val descriptionOfNote: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
