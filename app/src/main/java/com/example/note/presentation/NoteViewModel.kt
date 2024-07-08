package com.example.note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.Note
import com.example.note.data.NoteDao
import com.example.note.domain.NoteEvent
import com.example.note.data.NoteState
import com.example.note.data.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NoteViewModel(
    private val dao: NoteDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.RED_IMPORTANCE)
    private val _notes = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.RED_IMPORTANCE -> dao.getContactsByImportanceRed(SortType.RED_IMPORTANCE)
                SortType.YELLOW_IMPORTANCE -> dao.getContactsByImportanceYellow(SortType.YELLOW_IMPORTANCE)
                SortType.GREEN_IMPORTANCE -> dao.getContactsByImportanceGreen(SortType.GREEN_IMPORTANCE)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _state = MutableStateFlow(NoteState())


    val state = combine(_state, _sortType, _notes) { state, sortType, notes ->
        state.copy(
            notes = notes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())


    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }

            NoteEvent.SaveNote -> {
                val importance = state.value.importance
                val nameOfNote = state.value.nameOfNote
                val descriptionOfNote = state.value.descriptionOfNote



                if (nameOfNote.isBlank() || descriptionOfNote.isBlank()) {
                    return
                }

                val note = Note(
                    importance = importance,
                    nameOfNote = nameOfNote.trim(),
                    descriptionOfNote = descriptionOfNote.trim(),

                )

                viewModelScope.launch {
                    dao.addNote(note)
                }
                _state.update {
                    it.copy(
                        importance = SortType.RED_IMPORTANCE,
                        nameOfNote = "",
                        descriptionOfNote = "",
                    )
                }
            }

            is NoteEvent.SetImportance -> {
                _state.update {
                    it.copy(
                        importance = SortType.YELLOW_IMPORTANCE
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

            is NoteEvent.SortNote -> {
                _sortType.value = event.sortType
            }

        }
    }
}
