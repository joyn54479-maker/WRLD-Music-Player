-keep class com.wrld.musicplayer.** { *; }
-keep class androidx.media3.** { *; }
-keep interface androidx.media3.** { *; }
-keep enum androidx.media3.** { *; }
-keepclassmembers class androidx.media3.** { *; }

-keep class androidx.room.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase { *; }

-keep class com.google.dagger.** { *; }
-keep interface com.google.dagger.** { *; }

-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
