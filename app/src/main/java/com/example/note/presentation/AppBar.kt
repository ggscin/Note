package com.example.note.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.note.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
){

    val navigationIcon : (@Composable ()-> Unit)? =
        if (!title.contains("Your List")) {
            {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        tint = colorResource(id = R.color.pink),
                        contentDescription = null
                    )
                }
            }
        }else{
            null
        }


    if (navigationIcon != null) {
        TopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults
                .topAppBarColors(containerColor = colorResource(id = R.color.dark_Blue)),
            title = {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly) {
                    Text(text = title,
                        color = colorResource(id = R.color.pink),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .heightIn(max = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .border(1.dp, colorResource(id = R.color.pink))
                            .fillMaxWidth()
                            .size(0.dp, 1.dp)
                    )
                }
            },
            navigationIcon = navigationIcon
        )
    }
}