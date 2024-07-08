package com.example.note.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val nameOfNote: String,
    val descriptionOfNote: String,
    val importance: SortType,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
