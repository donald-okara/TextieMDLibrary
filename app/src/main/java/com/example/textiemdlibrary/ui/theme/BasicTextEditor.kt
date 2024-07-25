package com.example.textiemdlibrary.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.textiemdlibrary.AnnotationsManager
import com.example.textiemdlibrary.TextEditorVisualTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasicTextEditor(modifier: Modifier = Modifier){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val visualTransformation = remember { TextEditorVisualTransformer() }
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = BringIntoViewRequester()

    Column {
        Box(
            modifier = modifier
                .padding(16.dp)
        ) {
            if (text.text.isEmpty()) {
                Text(
                    text = "Tell your story...",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface)
                )
            }

            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .onFocusEvent { //This moves the BasicTextField to leave space for the keyboard
                        if (it.isFocused || it.hasFocus) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                value = text,
                onValueChange = { text = it },
                visualTransformation = visualTransformation,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
            )


        }

        Spacer(modifier = modifier.padding(8.dp))

        FormatButtons(
            modifier = modifier
                .bringIntoViewRequester(bringIntoViewRequester),
            text = text,
            onTextChange = { text = it }
        )
    }

}

@Composable
fun FormatButtons(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit
){
    val annotationsManager = remember { AnnotationsManager() }
    val scope = rememberCoroutineScope()
    val selection = text.selection
    val isBold = annotationsManager.isBold(text.text, selection.start, selection.end)
    val isItalics = annotationsManager.isItalics(text.text, selection.start, selection.end)
    val isStrikethrough = annotationsManager.isStrikethrough(text.text, selection.start, selection.end)
    val isMonospace = annotationsManager.isMonospace(text.text, selection.start, selection.end)


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )  {
        ControlWrapper(
            isSelected = isBold,
            onClick = {
                scope.launch(Dispatchers.Default) {
                    val updatedText = annotationsManager.applyBold(
                        text = text.text,
                        selectionStart = selection.start,
                        selectionEnd = selection.end
                    )
                    scope.launch(Dispatchers.Main) {
                        onTextChange(text.copy(text = updatedText))
                    }
                }
            }
        ) {
            Text("Bold")
        }


        ControlWrapper(
            isSelected = isItalics,
            onClick = {
                scope.launch(Dispatchers.Default) {
                    val updatedText = annotationsManager.applyItalics(
                        text = text.text,
                        selectionStart = selection.start,
                        selectionEnd = selection.end
                    )
                    scope.launch(Dispatchers.Main) {
                        onTextChange(text.copy(text = updatedText))
                    }
                }
            }
        ) {
            Text("Italic")
        }

        ControlWrapper(
            isSelected = isStrikethrough,
            onClick = {
                scope.launch(Dispatchers.Default) {
                    val updatedText = annotationsManager.applyStrikethrough(
                        text = text.text,
                        selectionStart = selection.start,
                        selectionEnd = selection.end
                    )
                    scope.launch(Dispatchers.Main) {
                        onTextChange(text.copy(text = updatedText))
                    }
                }
            }
        ) {
            Text("StrikethroughSpan")
        }

        ControlWrapper(
            isSelected = isMonospace,
            onClick = {
                scope.launch(Dispatchers.Default) {
                    val updatedText = annotationsManager.applyMonospace(
                        text = text.text,
                        selectionStart = selection.start,
                        selectionEnd = selection.end
                    )
                    scope.launch(Dispatchers.Main) {
                        onTextChange(text.copy(text = updatedText))
                    }
                }
            }
        ) {
            Text("Monospace")
        }
    }

}
@Composable
fun ControlWrapper(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            //.clip(CircleShape)
            .size(48.dp)
            .clickable { onClick() }
            .background(
                if (isSelected) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent,
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(all = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
