@file:Suppress("UNUSED_EXPRESSION")

package com.example.note.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.domain.NoteEvent
import com.example.note.data.NoteState
import com.example.note.data.SortType


@Composable
fun AddNote(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit,
    navController: NavController,

    ) {

    var buttonBorderColorGreen by remember { mutableStateOf(true) }
    var buttonBorderColorRed by remember { mutableStateOf(true) }
    var buttonBorderColorYellow by remember { mutableStateOf(true) }


    Scaffold(
        topBar = {
            Column {
                AppBarView(
                    title = "Add note"

                ) { navController.navigateUp() }
                Box(
                    modifier = Modifier
                        .border(1.dp, colorResource(id = R.color.pink))
                        .fillMaxWidth()
                        .size(0.dp, 1.dp)
                )
            }
        },
        floatingActionButton = {
            Button(
                modifier = Modifier,
                onClick = {
                    onEvent(NoteEvent.SaveNote)
                    navController.navigateUp()
                },
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
            }
        }

    ) { padding ->


        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(colorResource(id = R.color.dark_Blue))
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WishTextField(
                    label = "Name",
                    value = state.nameOfNote,
                    onValueChanged = { onEvent(NoteEvent.SetName(it)) },
                    maxLines = 1,
                    width = 20,
                    height = 60
                )

                Spacer(modifier = Modifier.height(10.dp))

                WishTextField(
                    label = "Description",
                    value = state.descriptionOfNote,
                    onValueChanged = { onEvent(NoteEvent.SetDescription(it)) },
                    maxLines = 100,
                    width = 100,
                    height = 200
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                Button(
                    onClick = {
                        state.importance = SortType.GREEN_IMPORTANCE
                        buttonBorderColorGreen = !buttonBorderColorGreen
                        buttonBorderColorRed = true
                        buttonBorderColorYellow = true
                    },
                    modifier = Modifier.size(85.dp, 25.dp),
                    shape = RoundedCornerShape(5.dp),
                    border = if (buttonBorderColorGreen) {
                        BorderStroke(2.dp, colorResource(id = R.color.green))
                    } else {
                        BorderStroke(2.dp, colorResource(id = R.color.pink))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green))
                ) {

                }

                Button(
                    onClick = {
                        state.importance = SortType.YELLOW_IMPORTANCE
                        buttonBorderColorYellow = !buttonBorderColorYellow
                        buttonBorderColorGreen = true
                        buttonBorderColorRed = true
                    },
                    shape = RoundedCornerShape(5.dp),
                    border = if (buttonBorderColorYellow) {
                        BorderStroke(2.dp, colorResource(id = R.color.yellou))
                    } else {
                        BorderStroke(2.dp, colorResource(id = R.color.pink))
                    },
                    modifier = Modifier.size(85.dp, 25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.yellou))
                ) {

                }

                Button(
                    onClick = {
                        state.importance = SortType.RED_IMPORTANCE
                        buttonBorderColorRed = !buttonBorderColorRed
                        buttonBorderColorGreen = true
                        buttonBorderColorYellow = true
                    },
                    shape = RoundedCornerShape(5.dp),
                    border = if (buttonBorderColorRed) {
                        BorderStroke(2.dp, colorResource(id = R.color.red))
                    } else {
                        BorderStroke(2.dp, colorResource(id = R.color.pink))
                    },
                    modifier = Modifier.size(85.dp, 25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red))
                ) {

                }
            }
        }
    }
}


@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    maxLines: Int,
    width: Int,
    height: Int
) {
    OutlinedTextField(
        maxLines = maxLines,
        value = value,
        onValueChange = onValueChanged,
        label = {
            Text(text = label, fontSize = 20.sp)
        },
        modifier = Modifier
            .fillMaxWidth()
            .size(width.dp, height.dp)
            .padding(start = 8.dp, end = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = colorResource(id = R.color.lightBlue),
            focusedBorderColor = colorResource(id = R.color.pink),
            unfocusedLabelColor = colorResource(id = R.color.lightBlue),
            focusedLabelColor = colorResource(id = R.color.pink),
            focusedTextColor = colorResource(id = R.color.pink),
            unfocusedTextColor = colorResource(id = R.color.pink),
        )
    )
}
