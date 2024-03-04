# Android List Display

This Android application demonstrates fetching, sorting, and displaying data from a S3 bucket. It uses Retrofit for network operations, a RecyclerView for displaying data, and custom layouts for headers and items.

## Features

- Fetches data from a remote JSON endpoint.
- Displays data in a RecyclerView with custom header and item views.
- Sorts and groups items based on their properties.

## Getting Started

### Prerequisites

- Android Studio Arctic Fox | 2020.3.1 or newer
- Android SDK with API Level 34
- Kotlin version compatible with 1.5.1 for Compose

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/MMN0001/Fetch-test.git
2. Open the project in Android Studio.

3. Ensure the SDK is set up correctly and that you've installed API Level 34 components.

### Build and Run

1. Select Run > Run 'app' in Android Studio.

2. Choose your device or an emulator to run the application.

## Project Configuration

### SDK Versions

- Compile SDK: 34
- Min SDK: 24
- Target SDK: 34

### Key Dependencies
- Jetpack Compose for UI design.
- Retrofit for network operations.
- RecyclerView for displaying lists.
- Jetpack Lifecycle for managing lifecycle-aware tasks.

### Kotlin and Compose Settings
- Kotlin JVM target set to 1.8.
- Kotlin Compiler Extension Version for Compose: 1.5.1.
