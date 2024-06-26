package com.example.note.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.domain.NoteEvent
import com.example.note.data.NoteState

@Composable
fun AddNote(
    viewModel: NoteViewModel,
    state: NoteState,
    onEvent: (NoteEvent) -> Unit,
    navController: NavController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(NoteEvent.SaveNote)
                    navController.navigateUp()
                }) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        },
        topBar = {
            AppBarView(
                title = "Add note"
            ) { navController.navigateUp() }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(colorResource(id = R.color.dark_Blue))
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Button(onClick = { /*TODO*/ }) {

                }
                Button(onClick = { /*TODO*/ }) {

                }
            }

            Column(
                Modifier
                    .fillMaxWidth(1f)
                    .padding(20.dp)) {
                OutlinedTextField(
                    value = state.nameOfNote,
                    onValueChange = { onEvent(NoteEvent.SetName(it)) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = state.descriptionOfNote,
                    onValueChange = { onEvent(NoteEvent.SetDescription(it)) },
                    modifier = Modifier.height(200.dp)
                )
            }
        }
    }
}