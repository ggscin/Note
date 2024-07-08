package com.example.note.domain

import com.example.note.data.Note
import com.example.note.data.SortType
import kotlinx.coroutines.flow.Flow

sealed interface NoteEvent {
    object SaveNote: NoteEvent
    data class SetName(val nameOfNote: String): NoteEvent
    data class SetDescription(val descriptionOfNote: String): NoteEvent
    data class SetImportance(val importance: SortType): NoteEvent
    data class SortNote(val sortType: SortType): NoteEvent
    data class DeleteNote(val note: Note): NoteEvent
}