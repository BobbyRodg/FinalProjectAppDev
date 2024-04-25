plugins {
    val room_version = "2.6.1"
    id("com.android.application") version "8.3.1" apply false
    id("com.android.library") version "8.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false

    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
    id("androidx.room") version "$room_version" apply false

}