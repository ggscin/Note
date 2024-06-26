package com.example.note.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.domain.NoteEvent
import com.example.note.data.NoteState

@Composable
fun ContactScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit,
    navController: NavController
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(NoteEvent.ShowDialog)
                    navController.navigate(Screen.EditScreen.rout)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
        },
    ) { padding ->

//        if (state.isAddingNote) {
//           navController.navigate(Screen.EditScreen.rout)
//        }

        LazyVerticalStaggeredGrid(
            contentPadding = padding,
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.dark_Blue)),
        ) {
            items(state.notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp, start = 8.dp, end = 8.dp)
                        .fillMaxHeight(1f)

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.pink))
                            .padding(top = 16.dp, start = 12.dp, end = 16.dp, bottom = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = note.nameOfNote,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.dark_Blue)
                            )

                            Box(
                                modifier = Modifier
                                    .background(
                                        color = colorResource(id = R.color.red),
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                    .size(20.dp)
                            ) {}
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .border(1.dp, colorResource(id = R.color.dark_Blue))
                                .fillMaxWidth()
                                .size(0.dp, 1.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = note.descriptionOfNote,
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.dark_Blue)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Время ", color = colorResource(id = R.color.dark_Blue))
                            Icon(
                                modifier = Modifier.clickable {
                                    onEvent(NoteEvent.DeleteNote(note))
                                },
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete contact",
                                tint = colorResource(id = R.color.dark_Blue)
                            )

                        }
                    }
                }
            }
        }
    }
}



