# Changelog

All notable changes to this project will be documented in this file. The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/) and this project adheres to [Semantic Versioning](https://semver.org/).

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
