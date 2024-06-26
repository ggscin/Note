package com.example.note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.Note
import com.example.note.data.NoteDao
import com.example.note.domain.NoteEvent
import com.example.note.data.NoteState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NoteDao
) : ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _contacts = _state
        .flatMapLatest { state ->
            dao.getAllNotes()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    val state = combine(_state, _contacts) { state, contacts ->
        state.copy(
            notes = contacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())



    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            NoteEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingNote = false
                    )
                }
            }

            NoteEvent.SaveNote -> {
                val nameOfNote = state.value.nameOfNote
                val descriptionOfNote = state.value.descriptionOfNote

                if (nameOfNote.isBlank() || descriptionOfNote.isBlank()) {
                    return
                }
                val note = Note(
                    nameOfNote = nameOfNote,
                    descriptionOfNote = descriptionOfNote,
                )
                viewModelScope.launch {
                    dao.addNote(note)
                }
                _state.update {
                    it.copy(
                        isAddingNote = false,
                        nameOfNote = "",
                        descriptionOfNote = "",
                    )
                }
            }

            is NoteEvent.SetName -> {
                _state.update {
                    it.copy(
                        nameOfNote = event.nameOfNote
                    )
                }
            }

            is NoteEvent.SetDescription -> {
                _state.update {
                    it.copy(
                        descriptionOfNote = event.descriptionOfNote
                    )
                }
            }

            is NoteEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingNote = true
                    )
                }
            }
        }
    }
}