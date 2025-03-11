# WorkManagerDemo
This Android demo project demonstrates how to use WorkManager with MVVM architecture and Kotlin to perform API calls in the background. The app makes sequential network requests using Retrofit and observes the progress with StateFlow. A loading screen (progress bar) is displayed while the API calls are in progress, and the UI updates dynamically based on the WorkManager's status. The app follows best practices for background work management in Android.

Key Features:
MVVM Architecture for better separation of concerns.
WorkManager to execute API calls in a background thread.
Retrofit for making network requests.
Coroutines & StateFlow for efficient, lifecycle-aware data handling.
Progress Indicator to show loading state while API calls execute.
