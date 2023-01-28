# Githubber
Githubber is simple version of a user and repository part of the GitHub app 📱
# About
It simply loads User and Repository data from API and shows it. Both data will be loaded the following way: loader → data from database → data from API Dummy API is used in this app. 
The list of users from this request: [https://api.github.com/users](https://api.github.com/users) 
The user’s repositories from this request: [https://api.github.com/users/{login}/repos](https://api.github.com/users/%7Blogin%7D/repos)
## Screencast
[Video](https://youtube.com/shorts/BzYMIB0mojI?feature=share)

## Screenshots
![HomePage](https://user-images.githubusercontent.com/91286770/215276531-31e1ab04-9b3b-4617-abf0-abc2bbaa1026.png)
![Repository Page](https://user-images.githubusercontent.com/91286770/215276543-1333e7c1-0830-4dc7-99d6-6edd5a487880.png)
## Built With  🛠
-  [Kotlin](https://kotlinlang.org/)  - First class and official programming language for Android development.
-  [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)  - For asynchronous and more.
-  [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.

- [Flow](https://developer.android.com/kotlin/flow)  - Data objects that notify views when the underlying changes.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)  - Stores UI-related data that isn't destroyed on UI changes.
- [Dependency Injection (Hilt)](https://developer.android.com/training/dependency-injection) - Design pattern in which an object or function receives other objects or functions that it depends on.
- [Retrofit](https://square.github.io/retrofit/)  - A type-safe HTTP client for Android and Java.
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- [Material](https://developer.android.com/jetpack/androidx/releases/compose-material) - Build Jetpack Compose UIs with ready-to-use Material Design Components. Support of DynamicColors.
# Package Structure

```
com.example.testtask    # App Module
.
├── presentation            # Store MainActivity / Compose Screens / Theme / VM, etc.
│
├── data                    # Network / Database / DataSource / Repository Impl / UseCase Impl
│
├── domain                  # Repository Interface / UseCase interface / Models / Mappers
│
└── di                      # AppModule / DataModule
