package com.example.note.data

data class NoteState(
    val notes: List<Note> = emptyList(),
    val nameOfNote: String = "",
    val descriptionOfNote: String = "",
    val isAddingNote: Boolean = false,
)
