package com.example.composablesearchapp

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ToolbarTitle() {
    Text(
        modifier = Modifier.padding(4.dp),
        text = "ComposableSearchApp",
        color = Color.White,
        fontSize = 20.sp
    )
}

@Composable
fun BackButton(
    iconState: Boolean,
    updateIconState: (Boolean) -> Unit,
) {
    Button(
        modifier = Modifier
            .heightIn(min = 1.dp)
            .widthIn(1.dp),
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(top = 1.dp, bottom = 0.dp),
        onClick = { updateIconState(!iconState) }
    ) {
        Image(
            modifier = Modifier.wrapContentSize(),
            painter = painterResource(id = R.drawable.ic_arrow_back_dark_grey),
            contentDescription = "back icon",
            contentScale = ContentScale.Inside
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun BackButtonPreview() {
    BackButton(
        iconState = false,
        updateIconState = {}
    )
}

@Composable
fun SearchButton(
    iconState: Boolean,
    updateIconState: (Boolean) -> Unit,
    searchIconPosition: SearchBarState,
) {
    IconButton(
        modifier = Modifier
            .size(36.dp)
            .offset(
                selectedPosition(transition(searchIconPosition)).x.dp,
                selectedPosition(transition(searchIconPosition)).y.dp
            ),
        onClick = {
            updateIconState(!iconState)
            getNextPosition(searchIconPosition)
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search_small),
            contentDescription = "search icon",
            contentScale = ContentScale.Inside
        )
    }
}

@ExperimentalAnimationApi
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SearchButtonPreview() {
    SearchButton(
        iconState = true,
        updateIconState = {},
        searchIconPosition = SearchBarState.COLLAPSED
    )
}

@ExperimentalAnimationApi
@Composable
fun SearchTextField(onTextChange: (String) -> Unit) {
    var textHint by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textHint,
        onValueChange = {
            textHint = it
            onTextChange(it)
        },
        placeholder = {
            Text(text = "Search ...", color = Color.White, fontSize = 19.sp)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        )
    )
}

@ExperimentalAnimationApi
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    SearchTextField {}
}
