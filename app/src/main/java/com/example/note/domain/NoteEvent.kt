package com.example.note.domain

import com.example.note.data.Note

sealed interface NoteEvent {
    object SaveNote: NoteEvent
    data class SetName(val nameOfNote: String): NoteEvent
    data class SetDescription(val descriptionOfNote: String): NoteEvent
    object ShowDialog: NoteEvent
    object HideDialog: NoteEvent
    data class DeleteNote(val note: Note): NoteEvent
}