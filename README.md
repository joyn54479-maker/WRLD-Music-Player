# WRLD Music Player

A complete offline Android music player built with modern Android technologies.

## Features

- 🎵 **Complete Music Playback** - Full control over your music with ExoPlayer integration
- 🎨 **Material 3 Design** - Modern UI with Jetpack Compose
- 📱 **Offline Support** - Play music without internet connection
- 🎧 **Playlist Management** - Create and organize your playlists
- 🔍 **Advanced Search** - Find songs by title, artist, or album
- 🎚️ **Playback Controls** - Shuffle, repeat, speed control
- 💾 **Local Database** - Room Database for storing song metadata
- 🔒 **Secure** - No permissions for online tracking

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Media Playback**: Media3 / ExoPlayer
- **Database**: Room Database
- **State Management**: StateFlow & LiveData
- **Dependency Injection**: Hilt
- **Architecture**: MVVM with Clean Architecture principles
- **Build System**: Gradle

## Project Structure

```
app/src/main/kotlin/com/wrld/musicplayer/
├── di/                 # Dependency Injection (Hilt Modules)
├── data/
│   └── repository/     # Data layer repositories
├── database/
│   ├── dao/           # Room Database Access Objects
│   └── entity/        # Database entity models
├── player/            # Media player logic
│   ├── PlayerViewModel
│   └── PlayerService
├── ui/
│   ├── screens/       # Compose UI screens
│   ├── theme/         # Material 3 theming
│   ├── viewmodel/     # Screen-specific ViewModels
│   └── MainActivity
└── WrldMusicPlayerApp # Hilt Application
```

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11 or higher
- Android SDK 24 (API level 24) or higher

### Building

```bash
# Clone the repository
git clone https://github.com/sneezerray/WRLD-Music-Player.git
cd WRLD-Music-Player

# Build APK
./gradlew assembleRelease

# Build AAB (for Play Store)
./gradlew bundleRelease
```

## CI/CD Pipeline

The project uses GitHub Actions for:
- Automated builds on push/PR
- Unit testing
- Lint analysis
- AAB and APK generation
- Artifact storage

## Permissions

- `READ_EXTERNAL_STORAGE` - Access music files
- `READ_MEDIA_AUDIO` - Android 13+ audio permission
- `FOREGROUND_SERVICE` - Background playback notification
- `INTERNET` - Future features

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

MIT License - feel free to use this project for personal or commercial purposes.

## Support

For issues, feature requests, or questions, please open an issue on GitHub.

---

**Made with ❤️ by sneezerray**
