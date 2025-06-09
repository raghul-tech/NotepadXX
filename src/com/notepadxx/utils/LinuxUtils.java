package com.notepadxx.utils;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;


//@SuppressWarnings("static-access")
public class LinuxUtils {

	 private static final String UPDATE_URL = "https://raw.githubusercontent.com/raghul-tech/NotepadXX-Releases/refs/heads/main/updates/updateForLinuxV1.2.0.json";
	    private static String currentVersion = "1.2.0";
	    private static long lastUpdateCheck = 0;
	    private static ScheduledExecutorService executorService;
	    private static boolean isCheckingForUpdate = false;
	  //  private static NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0();
	
	//to open file explorer 
	
	public static  void LinuxExplorer(File currentFile) {
	    try {
	        File directory;
	        if (currentFile != null && currentFile.exists()) {
	            directory = currentFile.getParentFile();  // Open the file's directory
	        } else {
	            String userHome = System.getProperty("user.home");
	            if (userHome != null) {
	                directory = new File(userHome);  // Open the user's home directory
	            } else {
	                directory = new File("/");  // Fallback to the root directory
	            }
	        }

	        if (directory != null && directory.exists() && directory.isDirectory()) {
	            // Commands to try in sequence
	            String[] commands = {
	                "xdg-open",   // Preferred method
	                "nautilus",   // GNOME
	                "dolphin",    // KDE
	                "thunar",     // XFCE
	                "pcmanfm",     // LXDE
	                "konqueror",  // Older KDE
	                "caja",       // MATE
	                "nemo",       // Cinnamon
	                "rofi"        // Lightweight setups
	            };

	            boolean opened = false;
	            for (String command : commands) {
	                try {
	                    Runtime.getRuntime().exec(new String[]{command, directory.getAbsolutePath()});
	                    opened = true;
	                    break;  // Exit loop if command succeeds
	                } catch (IOException ignored) {
	                    // Try the next command if current fails
	                }
	            }

	            if (!opened) {
	                JOptionPane.showMessageDialog(
	                		NotepadXXV1_2_0.getFrame(),
	                    "Could not open directory with any known file manager.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	                );
	            }
	        } else {
	            JOptionPane.showMessageDialog(
	            		NotepadXXV1_2_0.getFrame(),
	                "Invalid directory: " + directory,
	                "Error",
	                JOptionPane.ERROR_MESSAGE
	            );
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	        		NotepadXXV1_2_0.getFrame(),
	            "Unexpected error: " + e.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}
	
	
	//to open Terminal
	public static void LinuxTerminal(File currentFile) {
	    try {
	        File directory;
	        if (currentFile != null && currentFile.exists()) {
	            directory = currentFile.getParentFile();  // Open terminal in file's directory
	        } else {
	            String userHome = System.getProperty("user.home");
	            directory = new File(userHome != null ? userHome : "/");  // Fallback to home or root
	        }

	        if (directory.exists() && directory.isDirectory()) {
	            // Commands to try in sequence for different terminal emulators
	            String[][] commands = {
	            	{"qterminal", "--workdir", directory.getAbsolutePath()},
	                {"qterminal-l1on", "--working-directory=" + directory.getAbsolutePath()}, // Added qterminal-l1on
	                {"qterminal-lo1", "--working-directory=" + directory.getAbsolutePath()},  // Added qterminal-lo1
	                {"x-terminal-emulator", "--working-directory=" + directory.getAbsolutePath()}, // Generic terminal for most Linux
	                {"gnome-terminal", "--working-directory=" + directory.getAbsolutePath()}, // GNOME
	                {"konsole", "--workdir", directory.getAbsolutePath()}, // KDE
	                {"xfce4-terminal", "--working-directory=" + directory.getAbsolutePath()}, // XFCE
	                {"lxterminal", "--working-directory=" + directory.getAbsolutePath()}, // LXDE
	                {"xterm", "-e", "cd " + directory.getAbsolutePath()}, // Basic xterm
	                {"kitty", "--directory", directory.getAbsolutePath()},
	                {"alacritty", "--working-directory", directory.getAbsolutePath()}
	            };

	            boolean terminalOpened = false;
	            for (String[] command : commands) {
	                try {
	                    ProcessBuilder processBuilder = new ProcessBuilder(command);
	                    processBuilder.start();
	                    terminalOpened = true;
	                    break;  // Exit loop if terminal command succeeds
	                } catch (IOException e) {
	                    // Try next command if one fails
	                }
	            }

	            if (!terminalOpened) {
	                JOptionPane.showMessageDialog(
	                    NotepadXXV1_2_0.getFrame(),
	                    "Could not open a terminal with any known terminal emulator.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	                );
	            }
	        } else {
	            JOptionPane.showMessageDialog(
	                NotepadXXV1_2_0.getFrame(),
	                "Invalid directory: " + directory,
	                "Error",
	                JOptionPane.ERROR_MESSAGE
	            );
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	        		 NotepadXXV1_2_0.getFrame(),
	            "Unexpected error: " + e.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}
	
	
	//this is the fallback for root terminal
	public static void LinuxRootTerminalFallback(File currentFile) {
	    try {
	        // Prompt user for the password using a dialog
	        JPasswordField passwordField = new JPasswordField();
	        int option = JOptionPane.showConfirmDialog(
	            NotepadXXV1_2_0.getFrame(),
	            passwordField,
	            "Enter Root Password",
	            JOptionPane.OK_CANCEL_OPTION,
	            JOptionPane.PLAIN_MESSAGE
	        );

	        if (option != JOptionPane.OK_OPTION) {
	            return; // User canceled the input
	        }
	        char[] passwordChars = passwordField.getPassword();
            passwordField = null;
            if (passwordChars.length == 0) {
                JOptionPane.showMessageDialog(
                    NotepadXXV1_2_0.getFrame(),
                    "Password cannot be empty!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            String password = new String(passwordChars);
            Arrays.fill(passwordChars, '\0');
	        
	        if (password.isEmpty()) {
	            JOptionPane.showMessageDialog(
	            		 NotepadXXV1_2_0.getFrame(),
	                "Password cannot be empty!",
	                "Error",
	                JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }

	        File directory;
	        if (currentFile != null && currentFile.exists()) {
	            directory = currentFile.getParentFile(); // Open root terminal in file's directory
	        } else {
	            String userHome = System.getProperty("user.home");
	            directory = new File(userHome != null ? userHome : "/"); // Fallback to home or root
	        }

	        if (directory.exists() && directory.isDirectory()) {
	            // Commands for terminal emulators
	        	String[][] commands = {
	        		    {"echo", password, "|", "sudo", "-S", "qterminal", "--workdir", directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "qterminal-l1on", "--working-directory=" + directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "qterminal-lo1", "--working-directory=" + directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "x-terminal-emulator", "--working-directory=" + directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "gnome-terminal", "--working-directory=" + directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "konsole", "--workdir", directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "xfce4-terminal", "--working-directory=" + directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "lxterminal", "--working-directory=" + directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "xterm", "-e", "cd " + directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "kitty", "--directory", directory.getAbsolutePath()},
	        		    {"echo", password, "|", "sudo", "-S", "alacritty", "--working-directory", directory.getAbsolutePath()}
	        		};


	            boolean terminalOpened = false;
	            for (String[] command : commands) {
	                try {
	                    // Attempt to execute the command
	                    ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", String.join(" ", command));
	                    processBuilder.start();
	                    terminalOpened = true;
	                    break; // Exit loop if terminal opens successfully
	                } catch (IOException ignored) {
	                    // Ignore failures and try the next emulator
	                }
	            }
	            
	            password = null;

	            if (!terminalOpened) {
	                JOptionPane.showMessageDialog(
	                		 NotepadXXV1_2_0.getFrame(),
	                    "Could not open a root terminal with any known terminal emulator.",
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE
	                );
	            }
	        } else {
	            JOptionPane.showMessageDialog(
	            		 NotepadXXV1_2_0.getFrame(),
	                "Invalid directory: " + directory,
	                "Error",
	                JOptionPane.ERROR_MESSAGE
	            );
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	        		 NotepadXXV1_2_0.getFrame(),
	            "Unexpected error: " + e.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}

	//this is to open the root terminal 
	public static void LinuxRootTerminal(File currentFile) {
	    try {
	        File directory;
	        if (currentFile != null && currentFile.exists()) {
	            directory = currentFile.getParentFile();
	        } else {
	            String userHome = System.getProperty("user.home");
	            directory = new File(userHome != null ? userHome : "/");
	        }

	        if (!directory.exists() || !directory.isDirectory()) {
	            JOptionPane.showMessageDialog(
	                NotepadXXV1_2_0.getFrame(),
	                "Invalid directory: " + directory,
	                "Error",
	                JOptionPane.ERROR_MESSAGE
	            );
	            return;
	        }

	        String dirPath = directory.getAbsolutePath();
	        String commandToRun = "bash -c \"cd '" + dirPath + "' && sudo su\"";

	        // Terminals that support -e or --command execution
	        String[][] terminals = {
	            {"qterminal", "-e", commandToRun},
	            {"qterminal-l1on", "-e", commandToRun},
	            {"qterminal-lo1", "-e", commandToRun},
	            {"x-terminal-emulator", "-e", commandToRun},
	            {"gnome-terminal", "--", "bash", "-c", "cd '" + dirPath + "' && sudo su"},
	            {"konsole", "-e", "bash", "-c", "cd '" + dirPath + "' && sudo su"},
	            {"xfce4-terminal", "-e", "bash", "-c", "cd '" + dirPath + "' && sudo su"},
	            {"lxterminal", "-e", "bash", "-c", "cd '" + dirPath + "' && sudo su"},
	            {"xterm", "-e", "bash", "-c", "cd '" + dirPath + "' && sudo su"},
	            {"kitty", "-e", "bash", "-c", "cd '" + dirPath + "' && sudo su"},
	            {"alacritty", "-e", "bash", "-c", "cd '" + dirPath + "' && sudo su"}
	        };

	        boolean launched = false;
	        for (String[] terminal : terminals) {
	            try {
	                new ProcessBuilder(terminal).start();
	                launched = true;
	                break;
	            } catch (IOException e) {
	                // Continue to next
	            }
	        }

	        if (!launched) {
	           LinuxRootTerminalFallback(currentFile);
	            
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(
	            NotepadXXV1_2_0.getFrame(),
	            "Unexpected error: " + e.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}

	
	
	
	//Report for bug
    public static void LinuxReportBug() {
    	 String gitHubIssuesUrl = "https://github.com/raghul-tech/NotepadXX/issues/new?template=bug_report.md";
   	    try {
   	        // Try using the Desktop API to open the browser
   	        Desktop desktop = Desktop.getDesktop();
   	        if (desktop.isSupported(Desktop.Action.BROWSE)) {
   	            desktop.browse(new URI(gitHubIssuesUrl));
   	        }
   	    } catch (IOException | URISyntaxException e) {
   	        // If Desktop API fails, try fallback methods for Linux
   	        try {
   	            String[] commands = {
   	                "xdg-open", gitHubIssuesUrl, // Standard command for most Linux environments
   	                "google-chrome", gitHubIssuesUrl, // If Google Chrome is installed
   	                "firefox", gitHubIssuesUrl // If Firefox is installed
   	            };

   	            boolean browserOpened = false;
   	            for (String command : commands) {
   	                try {
   	                    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
   	                    processBuilder.start();
   	                    browserOpened = true;
   	                    break; // Exit the loop if browser opened successfully
   	                } catch (IOException ex) {
   	                    // If a command fails, move to the next one
   	                }
   	            }

   	            if (!browserOpened) {
   	                JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error: Unable to open browser. No supported browsers found.");
   	            }
   	        } catch (Exception ex) {
   	            // In case of other errors, show the message
   	            JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error: Unable to open the browser. " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
   	        }
   	    }
   	}
	
    
  //this is for support or donate 
    public static void LinuxDonate() {
   	    String gitHubIssuesUrl = "https://buymeacoffee.com/raghultech";
   	    try {
   	        // Try using the Desktop API to open the browser
   	        Desktop desktop = Desktop.getDesktop();
   	        if (desktop.isSupported(Desktop.Action.BROWSE)) {
   	            desktop.browse(new URI(gitHubIssuesUrl));
   	        }
   	    } catch (IOException | URISyntaxException e) {
   	        // If Desktop API fails, try fallback methods for Linux
   	        try {
   	            String[] commands = {
   	                "xdg-open", gitHubIssuesUrl, // Standard command for most Linux environments
   	                "google-chrome", gitHubIssuesUrl, // If Google Chrome is installed
   	                "firefox", gitHubIssuesUrl // If Firefox is installed
   	            };

   	            boolean browserOpened = false;
   	            for (String command : commands) {
   	                try {
   	                    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
   	                    processBuilder.start();
   	                    browserOpened = true;
   	                    break; // Exit the loop if browser opened successfully
   	                } catch (IOException ex) {
   	                    // If a command fails, move to the next one
   	                }
   	            }

   	            if (!browserOpened) {
   	                JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error: Unable to open browser. No supported browsers found.");
   	            }
   	        } catch (Exception ex) {
   	            // In case of other errors, show the message
   	            JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error: Unable to open the browser. " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
   	        }
   	    }
   	}
    
  //to open discord
    public static void LinuxDiscord() {
   	    String gitHubIssuesUrl = "https://discord.com/invite/MQn4GT8CVp";
   	    try {
   	        // Try using the Desktop API to open the browser
   	        Desktop desktop = Desktop.getDesktop();
   	        if (desktop.isSupported(Desktop.Action.BROWSE)) {
   	            desktop.browse(new URI(gitHubIssuesUrl));
   	        }
   	    } catch (IOException | URISyntaxException e) {
   	        // If Desktop API fails, try fallback methods for Linux
   	        try {
   	            String[] commands = {
   	                "xdg-open", gitHubIssuesUrl, // Standard command for most Linux environments
   	                "google-chrome", gitHubIssuesUrl, // If Google Chrome is installed
   	                "firefox", gitHubIssuesUrl // If Firefox is installed
   	            };

   	            boolean browserOpened = false;
   	            for (String command : commands) {
   	                try {
   	                    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
   	                    processBuilder.start();
   	                    browserOpened = true;
   	                    break; // Exit the loop if browser opened successfully
   	                } catch (IOException ex) {
   	                    // If a command fails, move to the next one
   	                }
   	            }

   	            if (!browserOpened) {
   	                JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error: Unable to open browser. No supported browsers found.");
   	            }
   	        } catch (Exception ex) {
   	            // In case of other errors, show the message
   	            JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error: Unable to open the browser. " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
   	        }
   	    }
   	}

    /**
     * Opens a file in Firefox or falls back to the default application.
     */
    public static void LinuxFireFox(File currentFile) {
   	File file = currentFile;
        String[] firefoxCommands = {"firefox", "mozilla-firefox"};
        openFileInBrowser(firefoxCommands, file, "Firefox not found, opening in the default application.");
    }

    /**
     * Opens a file in Google Chrome or falls back to the default application.
     */
    public static void LinuxGoogleChrome(File currentFile) {
   	 File file = currentFile;
        String[] chromeCommands = {"google-chrome", "chrome", "chromium-browser"};
        openFileInBrowser(chromeCommands, file, "Google Chrome not found, opening in the default application.");
    }

    /**
     * Opens a file in Tor Browser or falls back to the default application.
     */
    public static void LinuxTorBrowser(File currentFile) {
   	 File file = currentFile;
        String[] torCommands = {"tor-browser", "tor", "Tor-Browser", "torbrowser"};
        openFileInBrowser(torCommands, file, "Tor Browser not found, opening in the default application.");
    }

    /**
     * Utility method to check if a browser command is available on the system.
     */
    private static boolean isBrowserAvailable(String browserCommand) {
        try {
            Process process = new ProcessBuilder("which", browserCommand).start();
            return process.waitFor() == 0;
        } catch (Exception e) {
            return false;
        }
    }
	 
    /**
     * Opens the specified file in the provided browser. Falls back to the default system application.
     * 
     * @param browserCommands Array of potential browser commands to try (e.g., "firefox", "chrome").
     * @param file The file to be opened.
     * @param fallbackMessage Message to display if the browser is not found.
     */
    protected static void openFileInBrowser(String[] browserCommands, File file, String fallbackMessage) {
        try {
            String[] command = null; // Initialize the command
            boolean browserFound = false;

            // Check for the availability of each browser in the list
            for (String browserCommand : browserCommands) {
                if (isBrowserAvailable(browserCommand)) {
                    browserFound = true;
                    String filePath = file != null && file.exists()
                            ? file.getAbsolutePath().replace("\\", "/")
                            : ".";
                    command = new String[]{browserCommand, filePath};
                    break; // Exit loop once a valid browser command is found
                }
            }

            // Fallback to system's default file handler if no browser is found
            /*if (!browserFound) {
                JOptionPane.showMessageDialog(
                    null,
                    fallbackMessage,
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
                );
                if (file != null && file.exists()) {
                    Desktop.getDesktop().open(file); // Open with the default application
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "File not found or unavailable.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                return;
            }*/
                if (!browserFound) {
               	    JOptionPane.showMessageDialog(
               	        NotepadXXV1_2_0.getFrame(),
               	        fallbackMessage,
               	        "Info",
               	        JOptionPane.INFORMATION_MESSAGE
               	    );
               	    
               	    if (file != null && file.exists()) {
               	        try {
               	            // Convert the file path to a URL
               	            URL fileUrl = file.toURI().toURL();
               	            // Open the URL in the default web browser
               	            Desktop.getDesktop().browse(fileUrl.toURI());
               	        } catch (Exception e) {
               	            JOptionPane.showMessageDialog(
               	            	 NotepadXXV1_2_0.getFrame(),
               	                "Failed to open file in browser: " + e.getMessage(),
               	                "Error",
               	                JOptionPane.ERROR_MESSAGE
               	            );
               	        }
               	    } else {
               	        JOptionPane.showMessageDialog(
               	        	 NotepadXXV1_2_0.getFrame(),
               	            "File not found or unavailable.",
               	            "Error",
               	            JOptionPane.ERROR_MESSAGE
               	        );
               	    }
               	    return;
                }
            

            // Execute the command if a browser was found
            if (command != null) {
                new ProcessBuilder(command).start();
            } else {
                JOptionPane.showMessageDialog(
                		 NotepadXXV1_2_0.getFrame(),
                    "Failed to determine a valid browser command.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException | UnsupportedOperationException e) {
            JOptionPane.showMessageDialog(
            		 NotepadXXV1_2_0.getFrame(),
                "Error opening the file: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    
	
	/**
	 * 
	 * this is to check for update
	 * and update it and run the new Version 
	 * 
	 */
	
	public static void startUpdateScheduler() {
	    executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(() -> {
	        SwingUtilities.invokeLater(() -> checkForUpdates(false)); // Safe UI updates
	    }, 0, 24, TimeUnit.HOURS); // Check every 24 hours
	}

	protected void stopUpdateScheduler() {
	    if (executorService != null && !executorService.isShutdown()) {
	        executorService.shutdown();
	    }
	}
	
	protected static void checkForUpdates(boolean manualCheck) {
		if (isCheckingForUpdate) {
	        return; // Exit if an update check is already in progress
	    }

	    isCheckingForUpdate = true;
        long currentTime = System.currentTimeMillis();
        // Check if 24 hours have passed since the last update check
        if (!manualCheck && (currentTime - lastUpdateCheck < 24 * 60 * 60 * 1000)) {
        	//  isCheckingForUpdate = false;
            return; // Don't check again if within 24 hours
        }

        lastUpdateCheck = currentTime; // Update the last check time

        try {
       	// Fetch the update information from the JSON file
            URI uri = new URI(UPDATE_URL);
            URL url = uri.toURL();
            // Fetch the update information from the JSON file
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream responseStream = connection.getInputStream();
                UpdateInfo updateInfo = new Gson().fromJson(new InputStreamReader(responseStream), UpdateInfo.class);

                if (updateInfo != null) {
                    if (isNewVersionAvailable(updateInfo.latest_version)) {
                       // showUpdateDialog(updateInfo);
          
                            SwingUtilities.invokeLater(() -> {
                                showUpdateDialog(updateInfo);
                                //isCheckingForUpdate = false;
                 
                            });
                  
                    } else if (manualCheck) {
                        // Show "You are using the latest version" ONLY if this is a manual check
                        JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "You are using the latest version.", "Update Check", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else {
           	   
            }
        } catch (IOException| URISyntaxException  e) {
           // e.printStackTrace();
            if (manualCheck) {
                // Show an error dialog ONLY if this is a manual check
                JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error checking for updates.", "Update Check Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static boolean isNewVersionAvailable(String latestVersion) {
        return !currentVersion.equals(latestVersion);
    }

 private synchronized static void showUpdateDialog(UpdateInfo updateInfo) {
	  
	    String changelogMessage = updateInfo.changelog != null ? "<br/><br/>Changelog:<br/>" + updateInfo.changelog : "";

	    String message = "A new version (" + updateInfo.latest_version + ") is available." + changelogMessage +
	                     "<br/>Do you want to download it now?";

	    int response = JOptionPane.showConfirmDialog(NotepadXXV1_2_0.getFrame(),
	            "<html>" + message + "</html>", "Update Available",
	            JOptionPane.YES_NO_OPTION);

	    if (response == JOptionPane.YES_OPTION) {
	        downloadAndUpdate(updateInfo.download_url);
	    }
	}



 private static void downloadAndUpdate(String downloadUrl) {
     HttpURLConnection connection = null;
     try {
         // Download the new version
         URI uri = new URI(downloadUrl);
         URL url = uri.toURL();
         connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("GET");
         connection.setConnectTimeout(10000); // Set timeout for connection
         connection.setReadTimeout(10000); // Set timeout for reading the response
         connection.connect();

         // Check if the response code is HTTP_OK (200)
         if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
             JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Failed to connect to the server. Response code: " + connection.getResponseCode(), "Connection Error", JOptionPane.ERROR_MESSAGE);
             return;
         }

         // Create a temporary file to store the downloaded version
         String fileName = getFileNameFromURL(downloadUrl);
         File downloadDir = new File(System.getProperty("user.home") + "/Downloads");
      // Check if Downloads folder exists, and create it if it doesn't
         if (!downloadDir.exists()) {
             if (!downloadDir.mkdirs()) {
                 // If mkdirs fails, show the error and offer an alternative location
                 JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Failed to create 'Downloads' folder. Please ensure the folder exists or provide an alternative location.", "Folder Creation Error", JOptionPane.ERROR_MESSAGE);
                 
                 // Provide an option for the user to choose a different folder
                 JFileChooser folderChooser = new JFileChooser();
                 folderChooser.setDialogTitle("Select a folder to save the file");
                 folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                 
                 int returnValue = folderChooser.showSaveDialog(null);
                 if (returnValue == JFileChooser.APPROVE_OPTION) {
                     downloadDir = folderChooser.getSelectedFile();
                 } else {
                     return; // If the user cancels or doesn't choose a folder
                 }
             }
         }
         
         File newFile = new File(downloadDir, fileName);

         // Modern GUI for the download process
         showModernInstallerGUI(newFile, url, connection);
     } catch (Exception e) {
         JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error downloading the update: " + e.getMessage(), "Download Error", JOptionPane.ERROR_MESSAGE);
     } finally {
         if (connection != null) {
             connection.disconnect(); // Ensure connection is closed
         }
     }
 }

 private static void showModernInstallerGUI(File newFile, URL downloadUrl, HttpURLConnection connection) {
     JFrame installerFrame = new JFrame("Installing NotepadXX New Version");
     installerFrame.setSize(500, 150);
     installerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     installerFrame.setLocationRelativeTo(null);
     installerFrame.setIconImage(new ImageIcon(new NotepadXXV1_2_0().getClass().getResource("/icons/NotepadXXLogo.png")).getImage());

     JLabel statusLabel = new JLabel("Downloading NotepadXX New Version...");
     JProgressBar progressBar = new JProgressBar(0, 100);
     progressBar.setValue(0);
     progressBar.setStringPainted(true);
     progressBar.setFont(new Font("Arial", Font.PLAIN, 14));

     JButton finishButton = new JButton("Finish");
     finishButton.setEnabled(false);
     finishButton.addActionListener(e -> {
         installerFrame.dispose();
         runNewApplication(newFile); // Run the new file after finish
     });

     JPanel panel = new JPanel(new BorderLayout());
     panel.add(statusLabel, BorderLayout.NORTH);
     panel.add(progressBar, BorderLayout.CENTER);
     panel.add(finishButton, BorderLayout.SOUTH);
     installerFrame.add(panel);
     installerFrame.setVisible(true);

     SwingWorker<Void, Integer> downloader = new SwingWorker<>() {
         @Override
         protected Void doInBackground() throws Exception {
             try (InputStream inputStream = downloadUrl.openStream();
                  FileOutputStream outputStream = new FileOutputStream(newFile)) {

                 byte[] buffer = new byte[4096];
                 long totalBytesRead = 0;
                 long totalSize = connection.getContentLengthLong();

                 if (totalSize <= 0) {
                     JOptionPane.showMessageDialog(installerFrame, "Failed to get the file size. Download might not complete.", "Error", JOptionPane.ERROR_MESSAGE);
                     return null;
                 }

                 int bytesRead;
                 while ((bytesRead = inputStream.read(buffer)) != -1) {
                     outputStream.write(buffer, 0, bytesRead);
                     totalBytesRead += bytesRead;

                     // Update progress
                     int progress = (int) ((totalBytesRead * 100) / totalSize);
                     publish(progress);
                 }
             } catch (IOException e) {
                 JOptionPane.showMessageDialog(installerFrame, "Error during download: " + e.getMessage(), "Download Error", JOptionPane.ERROR_MESSAGE);
                 return null;
             }
             return null;
         }

         @Override
         protected void process(List<Integer> chunks) {
             int progress = chunks.get(chunks.size() - 1);
             progressBar.setValue(progress);
             statusLabel.setText("Downloading: " + progress + "%");
         }

         @Override
         protected void done() {
             statusLabel.setText("Download complete! "+newFile.getAbsolutePath());
             finishButton.setEnabled(true); // Enable  finish button when download is done
         }
     };

     downloader.execute();
 }

 private static void runNewApplication(File programFile) {
     try {
         // Extract the .tar.gz file
         if (programFile.getName().endsWith(".tar.gz")) {
             File extractedFolder = new File(programFile.getParentFile(), "NotepadXX_New");
             extractTarGz(programFile, extractedFolder);

             // Run install.sh from the extracted folder
             File installScript = new File(extractedFolder, "install.sh");
             if (installScript.exists()) {
                 ProcessBuilder pb = new ProcessBuilder("bash", installScript.getAbsolutePath());
                 pb.start();
                 JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Installation started!", "Success", JOptionPane.INFORMATION_MESSAGE);
             } else {
                 JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "install.sh not found in the extracted folder.", "Error", JOptionPane.ERROR_MESSAGE);
             }
         }else {
        	 JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "File Downloaded At "+programFile.getAbsolutePath() , "Support", JOptionPane.INFORMATION_MESSAGE);
         }
     } catch (IOException e) {
         JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "Error running the new version of the application: " + e.getMessage(), "Execution Error", JOptionPane.ERROR_MESSAGE);
     }
 }

 private static void extractTarGz(File tarGzFile, File outputDir) throws IOException {
     // Create output directory if it doesn't exist
     if (!outputDir.exists()) {
         outputDir.mkdir();
     }

     // Use a process to extract the .tar.gz file
     ProcessBuilder processBuilder = new ProcessBuilder("tar", "-xzvf", tarGzFile.getAbsolutePath(), "-C", outputDir.getAbsolutePath());
     Process process = processBuilder.start();
     try {
         int exitCode = process.waitFor();
         if (exitCode != 0) {
             throw new IOException("Failed to extract the tar.gz file.");
         }
     } catch (InterruptedException e) {
         throw new IOException("Extraction interrupted.", e);
     }
 }

 private static String getFileNameFromURL(String url) {
     try {
         URI uri = new URI(url);
         Path path = Paths.get(uri.getPath());
         return path.getFileName().toString();
     } catch (URISyntaxException e) {
         return "downloadedFile";
     }
 }

    private static class UpdateInfo {
        @SerializedName("latest_version")
        String latest_version;

        @SerializedName("download_url")
        String download_url; 

        @SerializedName("changelog")
        String changelog;
    }

 

    private static void manualUpdateCheck() {
        checkForUpdates(true); // Manual check for updates
    }

    // Call this method when the user selects "Check for Updates" from the menu
    public static void onCheckForUpdatesMenuSelected() {
        manualUpdateCheck(); // Invoke manual check for updates
        isCheckingForUpdate = false;
    }


    
}
