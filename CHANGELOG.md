# Changelog

All notable changes to this project will be documented in this file. The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) and this project adheres to [Semantic Versioning](https://semver.org/).

## [1.2.0] - 2025-06-9

### Added
- **Code Analysis & Linting**: Integrated syntax checking for various programming languages.
- **Splash Screen**: A new splash screen appears during app startup.
- **Unlimited File Opening**: Removed limits — open any number of files simultaneously.
- **Improved UI & UX**: UI is now smoother and more responsive.
- **Firefox and Tor Integration**: 
  - Firefox browser support for Windows.
  - Tor browser support for Linux.
- **Language and Code Analysis Menu**:
  - Detects and displays the language of the current file.
  - Users can toggle between normal and advanced error tooltips using `Ctrl + M`.
  - Use `Ctrl + Shift + M` to toggle visibility of tooltips.
- **Theme Display**: Shows the currently active theme.
- **Window Menu Bar**:
  - Displays a list of all open tabs as menu items.
- **Programming Language Icons**:
  - Each tab shows the respective programming language logo (e.g., Java icon for `.java` files).
- **Markdown Preview**:
  - `Ctrl + K`: Open preview in a new tab.
  - `Ctrl + Shift + K`: Open preview in a new window.
- **About NotepadXX**: Added "About" section in the Help menu.
- **Cross-platform Codebase**: A single codebase supports both Linux and Windows platforms.
- **Rename File Option**: Added a “Rename” option to the File menu.
- **Enhanced File Watcher**: Improved file monitoring for external changes.
- **Markdown Syntax Highlighting & Linting**: Support for Markdown files in the Language menu.
- **Preferences Menu Bar**:
  - **Theme Selector**: Choose between Light, Dark, Darcula, macOS, and other themes.
  - **Tooltip On/Off Toggle**: Turn tooltips completely on or off (for distraction-free writing).
  - **Tooltip Mode Switch**: Select between Normal or Advanced tooltips.
  - **Wrap Line Toggle**: Enable or disable word wrapping in the text area.



### Improved
- **Performance**: Large files now open faster without UI lag.
- **"Previous Tabs" Button**: Now works more reliably to restore previous sessions.
- **Tab Appearance**: Cleaned up tab names and layout; now includes logos and file names.

### Assets
- [Releases](https://github.com/raghul-tech/NotepadXX/releases)

### Fixed
- Minor bugs from version 1.1.0 related to tab UI, theme switching, and external file changes.

### Known Issues
- Wrapping long lines in the text area may cause a slight delay or lag, especially in large files.
- Markdown preview supports most emoji syntax, but emoji icons may not appear when the app is offline — an internet connection is required to render them fully.

---

## [1.1.0] - 2024-11-20
### Added
- **First official release of NotepadXX v1.1.0.**
- 6 themes (light and dark modes) to customize the editor's appearance.
- Multi-tab functionality to open and edit multiple files at once.
- Basic file management features: open, save, create, and modify text files.
- Syntax Highlighting: Built using RSyntaxTextArea, supporting a wide range of programming and markup languages, such as Java, Python, HTML, CSS, JavaScript, and more.
- Find and Replace functionality to search and replace text in the file.
- File Watcher: Automatically detects changes to files made by external programs and updates the content in the editor.
- Open large files efficiently with optimized performance.
- Ability to open and use system applications:
  - File Explorer.
  - Command Prompt (CMD).
  - Admin Command Prompt.
  - Open URLs directly in web browsers like Edge and Chrome.
- Full keyboard shortcuts support for faster text editing and file management.
- **Reopen Previous Tabs**: The app now remembers which files were open when closed, and will automatically reopen them upon restarting.

### Assets
- [Releases](https://github.com/raghul-tech/NotepadXX/releases)

### Fixed
- Fixed minor bugs related to the save and load file operations.
- Improved app performance for faster response while handling multiple files and large text data.

### Changed
- UI improvements for better readability and a more user-friendly experience.
- Enhanced stability when switching between tabs and handling external applications.

### Known Issues
- Syntax highlighting feature covers most popular programming languages but may not support all edge cases.
- Performance may vary slightly depending on the size of exceptionally large files or the number of open tabs.
- Tab Close Button UI: The close button on tabs may not update correctly when dealing with multiple tabs under certain conditions.
