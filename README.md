# News Android App

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.21-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-API_24%2B-blue.svg?logo=android)](https://developer.android.com/studio/releases/platforms)

A modern Android news aggregator built with Kotlin and Jetpack Compose, implementing Clean Architecture to deliver personalized news feeds with offline-first capabilities.

## Key Features

- **Reactive State Management**: ViewModel with StateFlow and sealed interfaces for type-safe UI commands
- **Offline-First Architecture**: Room database with background synchronization for cached articles access
- **Dependency Injection with Hilt**: Modular DI for testable code and repository pattern implementation
- **Asynchronous Data Processing**: Kotlin Coroutines and Flow for reactive streams with error handling
- **Material 3 UI Components**: Jetpack Compose with custom theming and responsive layouts

## Architecture

The app follows Clean Architecture principles with three main layers:

- **Presentation Layer**: Jetpack Compose UI components with ViewModels implementing MVVM pattern
- **Domain Layer**: Business logic with Use Cases and Repository interfaces
- **Data Layer**: Room database implementation with Repository concrete classes

```
app/
├── data/          # Data layer (Room, DAO, Repository impl)
├── domain/        # Domain layer (Use Cases, Repository interfaces)
└── presentation/  # Presentation layer (Compose UI, ViewModels)
```

## Tech Stack

- **Programming Language**: Kotlin
- **Architecture Pattern**: Clean Architecture with MVVM
- **UI Framework**: Jetpack Compose (Material 3)
- **Database**: Room (SQLite)
- **Dependency Injection**: Hilt
- **Async Programming**: Kotlin Coroutines and Flow
- **Build Tool**: Gradle with Kotlin DSL
- **Minimum Android SDK**: API 24 (Android 7.0)

## How to Build and Run

1. **Prerequisites**: Android Studio Iguana or later, JDK 11, and a NewsAPI.org account for API access

2. **Clone the Repository**:

   ```bash
   git clone https://github.com/meekieD/News.git
   cd News
   ```

3. **Configure API Key**: Create `keystore.properties` in the project root:
   ```
   NEWS_API_KEY="your_api_key_here"
   ```

4. **Build the Project**:

   ```bash
   ./gradlew build
   ```

5. **Run Unit Tests**:

   ```bash
   ./gradlew test
   ```

6. **Run on Device/Emulator**: Open in Android Studio and select Run > Run 'app' or use:

   ```bash
   ./gradlew installDebug
   ```

7. **Assemble Release APK** (optional):

    ```bash
    ./gradlew assembleRelease
    ```