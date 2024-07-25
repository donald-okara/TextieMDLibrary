## TextieMDLibrary Documentation

The `TextieMDLibrary` provides a comprehensive set of tools for parsing and applying Markdown-like formatting to text within Jetpack Compose applications. The library supports transformations for various styles such as bold, italics, strikethrough, monospace, headings, and hashtags. This documentation covers the primary components, classes, and functions provided by the library.

### Package Overview
- **Package Name:** `com.example.textiemdlibrary`
- **Main Components:**
    - `TextEditorVisualTransformer`: A custom `VisualTransformation` for rendering styled text.
    - Various transformation functions: Functions to apply specific text styles.
    - `AnnotationsManager`: A utility class for applying and checking specific annotations.

### Visual Transformation

#### `TextEditorVisualTransformer`

The `TextEditorVisualTransformer` class extends `VisualTransformation` to provide a custom transformation for displaying styled text.

##### Functions:

- **filter(text: AnnotatedString): TransformedText**
    - Transforms the input `AnnotatedString` by applying various text styles.
    - Applies the following transformations in order: bold, italics, hashtags, headings, strikethrough, monospace.

##### Usage:

To use the `TextEditorVisualTransformer` in a Jetpack Compose `TextField`:

```kotlin
val transformedText = TextEditorVisualTransformer().filter(AnnotatedString("Sample text"))
```

### Transformation Functions

These functions transform text based on custom-defined Markdown-like syntax and return a `Transformation` object containing the `AnnotatedString` and `OffsetMapping`.

- **transformHeading(text: AnnotatedString): Transformation**
    - Transforms text with `#` symbols into headings of different levels (1 to 4).
    - Example: `# Heading1` becomes a large bold heading.

- **transformItalics(text: AnnotatedString): Transformation**
    - Transforms text wrapped in `~~` into italicized text.
    - Example: `~~italic~~` becomes italicized text.

- **transformBold(text: AnnotatedString): Transformation**
    - Transforms text wrapped in `**` into bold text.
    - Example: `**bold**` becomes bold text.

- **transformStrikeThrough(text: AnnotatedString): Transformation**
    - Transforms text wrapped in `--` into strikethrough text.
    - Example: `--strikethrough--` becomes strikethrough text.

- **transformMonospace(text: AnnotatedString): Transformation**
    - Transforms text wrapped in `` ` `` into monospace text.
    - Example: `` `monospace` `` becomes monospace text.

- **transformHashtags(text: AnnotatedString): Transformation**
    - Transforms hashtags (words starting with `#`) into styled text.
    - Example: `#hashtag` becomes styled with a specific color and weight.

### Utility Functions

These functions are part of the `AnnotationsManager` class, which provides methods for applying and checking annotations within plain text.

#### `AnnotationsManager`

##### Functions:

- **applyBold(text: String, selectionStart: Int, selectionEnd: Int): String**
    - Applies or removes bold formatting (`**`) around the selected text or cursor position.

- **isBold(text: String, selectionStart: Int, selectionEnd: Int): Boolean**
    - Checks if the selected text is bolded.

- **applyItalics(text: String, selectionStart: Int, selectionEnd: Int): String**
    - Applies or removes italic formatting (`~~`) around the selected text or cursor position.

- **isItalics(text: String, selectionStart: Int, selectionEnd: Int): Boolean**
    - Checks if the selected text is italicized.

- **applyStrikethrough(text: String, selectionStart: Int, selectionEnd: Int): String**
    - Applies or removes strikethrough formatting (`--`) around the selected text or cursor position.

- **isStrikethrough(text: String, selectionStart: Int, selectionEnd: Int): Boolean**
    - Checks if the selected text is struckthrough.

- **applyMonospace(text: String, selectionStart: Int, selectionEnd: Int): String**
    - Applies or removes monospace formatting (`` `` ``) around the selected text or cursor position.

- **isMonospace(text: String, selectionStart: Int, selectionEnd: Int): Boolean**
    - Checks if the selected text is monospace.

### Data Classes

#### `Transformation`
- **Fields:**
    - `annotatedString: AnnotatedString`: The transformed text.
    - `offsetMapping: OffsetMapping`: The offset mapping for the transformation.

### Usage Example

To apply the transformations using the `AnnotationsManager`, instantiate the class and use the appropriate methods:

```kotlin
val annotationsManager = AnnotationsManager()
val text = "This is a **bold** and ~~italic~~ text."
val newText = annotationsManager.applyBold(text, 10, 16)  // Applies bold to "bold"
val isBold = annotationsManager.isBold(text, 10, 16)  // Checks if "bold" is bolded
```

### Conclusion

`TextieMDLibrary` offers a comprehensive set of tools for handling text styling in Jetpack Compose applications. By utilizing the provided visual transformations and utility functions, developers can easily implement rich text editing features in their apps.