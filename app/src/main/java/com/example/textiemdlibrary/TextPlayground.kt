package com.example.textiemdlibrary

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.textiemdlibrary.ui.theme.TextieMDLibraryTheme

@Composable
fun TextPlayground(){
    var text by remember {
        mutableStateOf(TextFieldValue(
            "# Heading"+
                "\n **Bold** " +
                "\n #Hashtag" +
                "\n --Strikethrough--" +
                "\n ``Monospace``"+
                "\n ~~Italics~~"+
                "\n Normal text"

        ))
    }
    val visualTransformation = remember {
        TextEditorVisualTransformer()
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        visualTransformation = visualTransformation,
        textStyle = TextStyle()
    )
}

@Composable
@Preview
fun TextPlaygroundPreview(){
    TextieMDLibraryTheme {
        TextPlayground()
    }
}