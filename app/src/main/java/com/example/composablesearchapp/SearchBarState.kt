package com.example.composablesearchapp

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset

enum class SearchBarState { COLLAPSED, EXPANDED }

@Composable
fun transition(searchIconPosition: SearchBarState): Transition<SearchBarState> {
    return updateTransition(targetState = searchIconPosition, label = "search icon")
}

@Composable
fun selectedPosition(transition: Transition<SearchBarState>): Offset {
    val selectedPosition by transition.animateOffset(label = "search bar") { state ->
        when (state) {
            SearchBarState.EXPANDED -> Offset(-300f, 0f)
            SearchBarState.COLLAPSED -> Offset(0f, 0f)
        }
    }
    return selectedPosition
}

fun getNextPosition(searchIconPosition: SearchBarState) =
    when (searchIconPosition) {
        SearchBarState.COLLAPSED -> SearchBarState.EXPANDED
        SearchBarState.EXPANDED -> SearchBarState.COLLAPSED
    }
