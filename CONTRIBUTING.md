# Contributing to NotepadXX

👋 Hey, thanks for considering contributing to **NotepadXX** — a cross-platform modern Java-based text editor.

This guide will help you understand how to contribute, set up the project locally, and follow the best practices.

---

## 📁 Project Structure

- Main Entry File: `com.notepadxx.notepadxx.NotepadXXV1_2_0.java`
- Written in Java 23
- Uses external JARs and JavaFX
- OS-Specific logic is handled within the code (Linux & Windows)

---

## 🧩 Dependencies

You’ll need the following JARs in your classpath:

- [FlatLaf](https://github.com/JFormDesigner/FlatLaf)
- [Gson](https://github.com/google/gson)
- [ANTLR](https://github.com/antlr/antlr4)
- [RSyntaxTextArea](https://github.com/bobbylight/RSyntaxTextArea)
- [Flexmark-All](https://github.com/vsch/flexmark-java)
- JavaFX SDK (`javafx.controls`, `javafx.web`, etc.)

Use your IDE (e.g., IntelliJ, Eclipse) or a build tool (like Gradle/Maven if added later) to manage these.

---

## ⚙️ Setting Up Locally

1. **Clone the Repo**  
   ```bash
    git clone https://github.com/raghul-tech/NotepadXX.git
   
     cd NotepadXX
   ```
2. **Open in IDE**   
- Open the project folder and make sure:
	- Your using `JDK23` for both `java` and `javaFX`
	- All required JARs are added to the classpath

3. **Run the App**

- Run the main file using the Run ▶️ button in Eclipse.
	- Entry Point `com.notepadxx.notepadxx.NotepadXXV1_2_0`
- If running from terminal (optional for advanced users):
	```bash
	java -cp ".:lib/*" com.notepadxx.notepadxx.NotepadXXV1_2_0
     ```
     > On Windows, use `;` instead of `:` in the classpath:
     ```bash
	java -cp ".;lib/*" com.notepadxx.notepadxx.NotepadXXV1_2_0
     ```
     
4. **Cross-Platform Support**
- The app auto-detects the OS and switches code execution accordingly (Linux or Windows).

---

##  🧠 Contribution Ideas

- Improve UI themes, responsiveness, or layout

- Add new programming language logos in tabs

- Improve Markdown preview (emoji support)

- Fix cross-platform bugs

- Add more integrations or keyboard shortcuts

- Improve tab/window management logic

---

## ✅ Pull Request Guide

1. Fork the repo and create a branch
	```bash
	git checkout -b fix/linux-tab-crash
	```
2. Make changes, commit, and push:
   ```bash
		git commit -m "Fix: Linux tab UI crash issue"
		git push origin fix/linux-tab-crash
	```
3. Submit a Pull Request with:
	- What you changed
	- How you tested
	- Any screenshots (if UI)
	
---
	
## 	🧼 Code Style

- Use meaningful variable and method names.

- Comment your code when logic is complex or OS-specific.

- Stick to Java coding conventions.

- Keep the project modular and maintainable.

---

## 🐛 Reporting Issues

- Use the [GitHub Issues](https://github.com/raghul-tech/NotepadXX/issues/new?template=bug_report.md) tab to report bugs or request features
- Include OS, Java version, and screenshots if applicable

---

## 	📄 License
NotepadXX is an open source project. See the [LICENSE](LICENSE) file for more information.
	
---	
	
## 	🙌 Thank You!

Every issue, pull request, and suggestion makes NotepadXX better. Thanks for being awesome! 🚀
	
	
