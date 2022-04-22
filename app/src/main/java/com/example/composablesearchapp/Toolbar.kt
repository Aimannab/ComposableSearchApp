package com.example.composablesearchapp

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun Toolbar() {
    var toolbarTitleState by remember { mutableStateOf(true) }
    val searchIconPosition by remember { mutableStateOf(SearchBarState.COLLAPSED) }

    TopAppBar(
        modifier = Modifier.background(
            Brush.horizontalGradient(
                colors = listOf(
                    Color(0XFF4568dc),
                    Color(0XFFb06ab3)
                )
            )
        ),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Toolbar title
            AnimatedVisibility(
                visible = toolbarTitleState,
                enter = slideInHorizontally()
                        + expandHorizontally(expandFrom = Alignment.Start)
                        + fadeIn(initialAlpha = 0.3f),
                exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut()
            ) {
                ToolbarTitle()
            }
            //Back button
            AnimatedVisibility(
                visible = !toolbarTitleState,
                enter = slideInHorizontally()
                        + expandHorizontally(expandFrom = Alignment.Start)
                        + fadeIn(initialAlpha = 0.3f),
                exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut()
            ) {
                BackButton(
                    iconState = toolbarTitleState,
                    updateIconState = { newIconState -> toolbarTitleState = newIconState }
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            //Search button
            Box(
                modifier = if (toolbarTitleState) Modifier.fillMaxWidth() else Modifier.wrapContentWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                SearchButton(
                    iconState = toolbarTitleState,
                    updateIconState = { newIconState -> toolbarTitleState = newIconState },
                    searchIconPosition = searchIconPosition
                )
            }
            //EditText bar
            AnimatedVisibility(
                visible = !toolbarTitleState,
                enter = slideInHorizontally()
                        + expandHorizontally(expandFrom = Alignment.Start)
                        + fadeIn(initialAlpha = 0.3f),
                exit = slideOutHorizontally() + shrinkHorizontally() + fadeOut()
            ) {
                var text = ""
                SearchTextField(onTextChange = { text = it })
            }
        }
    }
}
