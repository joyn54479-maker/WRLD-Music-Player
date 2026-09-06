# Proguard rules for WRLD Music Player
-keep class com.wrld.musicplayer.** { *; }
-keep class androidx.media3.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.room.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep views
-keep class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
}

# Keep view constructors for Reflection
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
