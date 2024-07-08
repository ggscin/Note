package com.example.note.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Note::class],
    version = 2
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val dao : NoteDao
}