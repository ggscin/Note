package com.example.note.presentation

sealed class Screen(val rout:String) {
    data object NoteScreen: Screen("note_screen")
    data object EditScreen: Screen("edit_screen")
}