package com.example.note.data

data class NoteState(
    val notes: List<Note> = emptyList(),
    val nameOfNote: String = "",
    val descriptionOfNote: String = "",
    var importance: SortType = SortType.RED_IMPORTANCE,
    var sortType: SortType = SortType.RED_IMPORTANCE,
    val getCountRed: Int = 0,
    val getCountYellow: Int = 0,
    val getCountGreen: Int = 0
)
