package com.example.textiemdlibrary

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

val HASHTAG_REGEX_PATTERN = Regex(pattern = "(#[A-Za-z0-9-_]+)(?:#[A-Za-z0-9-_]+)*")
val BOLD_REGEX_PATTERN = Regex(pattern = "(\\*{2})(\\s*\\b)([^\\*]*)(\\b\\s*)(\\*{2})")
val SRIKETHROUGH_REGEX_PATTERN = Regex(pattern = "(\\-{2})(\\s*\\b)([^\\*]*)(\\b\\s*)(\\-{2})")
val MONOSPACE_REGEX_PATTERN = Regex(pattern = "(\\`{2})(\\s*\\b)([^\\*]*)(\\b\\s*)(\\`{2})")
val ITALICS_REGEX_PATTERN = Regex(pattern = "(\\~{2})(\\s*\\b)([^\\*]*)(\\b\\s*)(\\~{2})")
val HEADING_REGEX_PATTERN = Regex(pattern = "\\#{1,4}\\s([^\n\\#]*)")

class TextEditorVisualTransformer : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var transformation = transformBold(text = text)
        transformation = transformItalics(text = transformation.annotatedString)
        transformation = transformHashtags(text = transformation.annotatedString)
        transformation = transformHeading(text = transformation.annotatedString)
        transformation = transformStrikeThrough(text = transformation.annotatedString)
        transformation = transformMonospace(text = transformation.annotatedString)
        return convertToTransformedText(transformation)
    }

    private fun convertToTransformedText(transformation: Transformation): TransformedText {
        return TransformedText(text = transformation.annotatedString, offsetMapping = transformation.offsetMapping)
    }
}

fun transformHeading(text: AnnotatedString): Transformation {
    val matches = HEADING_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            val headingLevel = getHeadingLevel(match.value)
            val sizeList = listOf(32.sp, 28.sp, 24.sp, 18.sp)
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = sizeList[headingLevel - 1] / 4),
                matchRange.first,
                matchRange.first + headingLevel
            )
            builder.addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = sizeList[headingLevel - 1]),
                matchRange.first + headingLevel,matchRange.last + 1 // Adjusted to end of match
            )
            // Removed the styling for ending hashes
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

fun transformItalics(text: AnnotatedString): Transformation {
    val matches = ITALICS_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.first,
                matchRange.first + 2
            )
            builder.addStyle(style = SpanStyle(fontStyle = FontStyle.Italic), matchRange.first + 2, matchRange.last - 1)
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.last - 1,
                matchRange.last + 1
            )
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

fun transformBold(text: AnnotatedString): Transformation {
    val matches = BOLD_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.first,
                matchRange.first + 2
            )
            builder.addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), matchRange.first + 2, matchRange.last - 1)
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.last - 1,
                matchRange.last + 1
            )
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

fun transformStrikeThrough(text: AnnotatedString): Transformation {
    val matches = SRIKETHROUGH_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.first,
                matchRange.first + 2
            )
            builder.addStyle(style = SpanStyle(fontStyle = FontStyle.Italic, textDecoration = TextDecoration.LineThrough, fontWeight = FontWeight.SemiBold), matchRange.first + 2, matchRange.last - 1)
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.last - 1,
                matchRange.last + 1
            )
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}


fun transformMonospace(text: AnnotatedString): Transformation {
    val matches = MONOSPACE_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.first,
                matchRange.first + 2
            )
            builder.addStyle(style = SpanStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.SemiBold, background = Color.Gray), matchRange.first + 2, matchRange.last - 1)
            builder.addStyle(
                style = SpanStyle(color = Color.Transparent, baselineShift = BaselineShift.Superscript, fontSize = 0.0.sp),
                matchRange.last - 1,
                matchRange.last + 1
            )
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

fun transformHashtags(text: AnnotatedString): Transformation {
    val builder = AnnotatedString.Builder(text)
    val matches = HASHTAG_REGEX_PATTERN.findAll(text.text)
    for (match in matches) {
        val matchRange = match.range
        builder.addStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold), start = matchRange.first, end = matchRange.last + 1)
    }
    return Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
}

private fun getHeadingLevel(text: String): Int {
    var i = 0
    while (i < text.length) {
        if (text[i] == '#') {
            i++
        } else {
            break
        }
    }
    return i
}


data class Transformation(val annotatedString: AnnotatedString, val offsetMapping: OffsetMapping)
