package com.example.note.presentation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note.domain.NoteEvent
import com.example.note.data.NoteState


@Composable
fun Navigation(
    viewModel: NoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController(),
    state: NoteState,
    onEvent: (NoteEvent) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NoteScreen.rout
    ) {
        composable(Screen.NoteScreen.rout){
            ContactScreen(state = state, onEvent = onEvent, navController = navController)
        }

        composable(Screen.EditScreen.rout){
            AddNote(state = state, onEvent = onEvent, navController = navController, viewModel = viewModel)
        }
    }
}