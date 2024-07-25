## TextieMDLibrary

TextieMDLibrary provides tools for applying Markdown-like formatting to text in Jetpack Compose applications, including styles like bold, italics, strikethrough, monospace, headings, and hashtags.

[video](Screen_recording_20240724_113848.mp4)

### Key Components

#### `TextEditorVisualTransformer`
A `VisualTransformation` that styles text with:
- **Bold:** `**bold**`
- **Italics:** `~~italic~~`
- **Strikethrough:** `--strikethrough--`
- **Monospace:** `` `monospace` ``
- **Headings:** `# Heading`
- **Hashtags:** `#hashtag`

#### Installation
1. Add jitpack.io to settings.gradle.kts

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("jitpack.io")
    }
}
```

2. Add the dependancy to app level build.gradle.kts
```kotlin
    implementation("com.github.donald-okara:TextieMDLibrary:1.0.4")
```

> Check https://jitpack.io/#donald-okara/TextieMDLibrary for last stable version

##### Usage:
```kotlin
val transformedText = TextEditorVisualTransformer().filter(AnnotatedString("Sample text"))
```

#### `AnnotationsManager`
Utility class for applying/checking text styles.

##### Key Methods:
- **applyBold(text, start, end): String**
- **isBold(text, start, end): Boolean**
- **applyItalics(text, start, end): String**
- **isItalics(text, start, end): Boolean**
- **applyStrikethrough(text, start, end): String**
- **isStrikethrough(text, start, end): Boolean**
- **applyMonospace(text, start, end): String**
- **isMonospace(text, start, end): Boolean**

### Example Usage
```kotlin
val annotationsManager = AnnotationsManager()
val newText = annotationsManager.applyBold("This is **bold** text.", 8, 14)
val isBold = annotationsManager.isBold(newText, 8, 14)
```

For more details, refer to the complete [documentation](Documentation.md) or the source code.