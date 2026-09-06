# Keep line numbers for crash reporting
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep all public classes and methods
-keep public class * { public *; }

# Compose related
-keep class androidx.compose.** { *; }
-keepclasseswithmembernames class androidx.compose.** { *; }

# Material 3
-keep class com.google.android.material.** { *; }

# ExoPlayer/Media3
-keep class androidx.media3.** { *; }
-keepclasseswithmembernames class androidx.media3.** { *; }

# Room
-keep class androidx.room.** { *; }
-keepclasseswithmembernames class * {
    @androidx.room.* <fields>;
    @androidx.room.* <methods>;
}

# Hilt
-keep class * implements dagger.Referenceable
-keep @dagger.hilt.** class *

# Kotlin
-keepclasseswithmembernames class kotlinx.** { public <methods>; }
-dontwarn kotlin.**
-dontwarn kotlinx.**

# DataStore
-keep class androidx.datastore.** { *; }

# Coil
-keep class coil.** { *; }
-keepclasseswithmembernames class coil.** { *; }

# R8/Proguard general rules
-verbose
-keepattributes *Annotation*
-keepattributes InnerClasses
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider
-keep class * extends android.app.backup.BackupAgent
-keep class * extends android.preference.Preference
-keep class * extends android.view.View
-keep class * extends android.app.Service
-keep interface android.app.Service
-keep class * extends android.content.Intent

# Keep all enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep parcelable classes
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep serializable classes
-keep class * implements java.io.Serializable { *; }

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
