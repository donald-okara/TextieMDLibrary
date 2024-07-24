package com.example.textiemdlibrary

import android.util.Log

class AnnotationsManager {
    private val TAG = "AnnotationsManager"

    fun applyBold(text: String, selectionStart: Int, selectionEnd: Int, annotation: String = "**"): String {

        /**
         * Function to apply $annotation around the selected text
         */

        Log.d(TAG, "applyBold called with text: $text, selectionStart: $selectionStart, selectionEnd: $selectionEnd")

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val beforeSelection = text.substring(0, start)
            val selectedText = text.substring(start, end)
            val afterSelection = text.substring(end)

            val result = if (selectedText.startsWith(annotation) && selectedText.endsWith(annotation)) {
                val cleanText = selectedText.removeSurrounding(annotation)
                "$beforeSelection$cleanText$afterSelection"
            } else {
                "$beforeSelection**$selectedText**$afterSelection"
            }

            Log.d(TAG, "Result after applying/removing bold: $result")
            return result
        } else {
            val beforeCursor = text.substring(0, start)
            val afterCursor = text.substring(start)

            val result = "$beforeCursor**$afterCursor**"

            Log.d(TAG, "Result after applying bold around cursor: $result")
            return result
        }
    }

    fun isBold(text: String, selectionStart: Int, selectionEnd: Int, annotation: String = "**"): Boolean {

        /**
         * Function to check if the selected text is bolded
         */

        Log.d(TAG, "isBold called with text: $text, selectionStart: $selectionStart, selectionEnd: $selectionEnd")

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val selectedText = text.substring(start, end)
            return selectedText.startsWith(annotation) && selectedText.endsWith(annotation)
        }

        val beforeCursor = text.substring(0, start)
        val afterCursor = text.substring(start)

        return beforeCursor.endsWith(annotation) && afterCursor.startsWith(annotation)
    }

    fun applyItalics(text: String, selectionStart: Int, selectionEnd: Int, annotation: String = "~~"): String {

        /**
         * Function to apply $annotation around the selected text
         */

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val beforeSelection = text.substring(0, start)
            val selectedText = text.substring(start, end)
            val afterSelection = text.substring(end)

            val result = if (selectedText.startsWith(annotation) && selectedText.endsWith(annotation)) {
                val cleanText = selectedText.removeSurrounding(annotation)
                "$beforeSelection$cleanText$afterSelection"
            } else {
                "$beforeSelection~~$selectedText~~$afterSelection"
            }

            return result
        } else {
            val beforeCursor = text.substring(0, start)
            val afterCursor = text.substring(start)

            return "$beforeCursor~~$afterCursor~~"
        }
    }

    fun isItalics(text: String, selectionStart: Int, selectionEnd: Int, annotation: String): Boolean {

        /**
         * Function to check if the selected text is italicized
         */

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val selectedText = text.substring(start, end)
            return selectedText.startsWith(annotation) && selectedText.endsWith(annotation)
        }

        val beforeCursor = text.substring(0, start)
        val afterCursor = text.substring(start)

        return beforeCursor.endsWith(annotation) && afterCursor.startsWith(annotation)
    }

    fun applyStrikethrough(text: String, selectionStart: Int, selectionEnd: Int, annotation: String = "--"): String {

        /**
         * Function to apply $annotation around the selected text
         */

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val beforeSelection = text.substring(0, start)
            val selectedText = text.substring(start, end)
            val afterSelection = text.substring(end)

            val result = if (selectedText.startsWith(annotation) && selectedText.endsWith(annotation)) {
                val cleanText = selectedText.removeSurrounding(annotation)
                "$beforeSelection$cleanText$afterSelection"
            } else {
                "$beforeSelection--$selectedText--$afterSelection"
            }

            return result
        } else {
            val beforeCursor = text.substring(0, start)
            val afterCursor = text.substring(start)

            return "$beforeCursor--$afterCursor--"
        }
    }

    fun isStrikethrough(text: String, selectionStart: Int, selectionEnd: Int, annotation: String = "--"): Boolean {

        /**
         * Function to check if the selected text is struckthrough
         */

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val selectedText = text.substring(start, end)
            return selectedText.startsWith(annotation) && selectedText.endsWith(annotation)
        }

        val beforeCursor = text.substring(0, start)
        val afterCursor = text.substring(start)

        return beforeCursor.endsWith(annotation) && afterCursor.startsWith(annotation)
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun applyMonospace(text: String, selectionStart: Int, selectionEnd: Int, annotation: String = "``"): String {

        /**
         * Function to apply $annotation around the selected text
         */

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val beforeSelection = text.substring(0, start)
            val selectedText = text.substring(start, end)
            val afterSelection = text.substring(end)

            val result = if (selectedText.startsWith(annotation) && selectedText.endsWith(annotation)) {
                val cleanText = selectedText.removeSurrounding(annotation)
                "$beforeSelection$cleanText$afterSelection"
            } else {
                "$beforeSelection``$selectedText``$afterSelection"
            }

            return result
        } else {
            val beforeCursor = text.substring(0, start)
            val afterCursor = text.substring(start)

            return "$beforeCursor``$afterCursor``"
        }
    }

    fun isMonospace(text: String, selectionStart: Int, selectionEnd: Int, annotation: String = "``"): Boolean {

        /**
         * Function to check if the selected text is monospace
         */

        val start = minOf(selectionStart, selectionEnd)
        val end = maxOf(selectionStart, selectionEnd)

        if (start != end) {
            val selectedText = text.substring(start, end)
            return selectedText.startsWith(annotation) && selectedText.endsWith(annotation)
        }

        val beforeCursor = text.substring(0, start)
        val afterCursor = text.substring(start)

        return beforeCursor.endsWith(annotation) && afterCursor.startsWith(annotation)
    }
}
