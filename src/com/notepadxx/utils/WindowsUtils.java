package com.notepadxx.utils;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.notepadxx.notepadxx.NotepadXXV1_2_1;

//@SuppressWarnings("static-access")
public class WindowsUtils {
	    private static final String UPDATE_URL = "https://raw.githubusercontent.com/raghul-tech/NotepadXX-Releases/refs/heads/main/updates/updateforWindowsV1.2.1.json";
	    private static String currentVersion = "1.2.1";
	    private static long lastUpdateCheck = 0;
	    private static ScheduledExecutorService executorService;
	    private static boolean isCheckingForUpdate = false;
	  //  private static NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0(); 

	public static void WindowsExplorer(File currentFile) {
		  try {
		      File directory;
		      if (currentFile != null && currentFile.exists()) {
		          directory = currentFile.getParentFile();  // Open file's directory
		      } else {
		          directory = new File(System.getProperty("user.home"));  // Open user's home directory
		      }
		      Desktop.getDesktop().open(directory);  // Opens File Explorer
		  } catch (IOException e) {
		      JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error opening folder in Explorer: " + e.getMessage());
		  }
		}
	
	public static void WindowsCmd(File currentFile) {
		  try {
		      File directory;
		      if (currentFile != null && currentFile.exists()) {
		          directory = currentFile.getParentFile();  // Open CMD in file's directory
		      } else {
		          directory = new File(System.getProperty("user.home"));  // Open CMD in user's home directory
		      }

		      // Build the command to open CMD in the respective folder
		     // String cmd = "cmd /c start cmd.exe /K \"cd /d " + directory.getAbsolutePath() + "\"";
		      //Runtime.getRuntime().exec(cmd);
		   // Build the command to open CMD in the respective folder
		      String[] command = {"cmd", "/c", "start", "cmd.exe", "/K", "cd /d " + directory.getAbsolutePath()};

		      // Use ProcessBuilder to execute the command
		      ProcessBuilder processBuilder = new ProcessBuilder(command);
		      processBuilder.start();
		  } catch (IOException e) {
		      JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error opening folder in CMD: " + e.getMessage());
		  }
		}
	
	//this is to open root cmd
	public static void WindowsAdminCmd(File currentFile) {
	  try {
	      File directory;
	      if (currentFile != null && currentFile.exists()) {
	          directory = currentFile.getParentFile();  // Open CMD in file's directory
	      } else {
	          directory = new File(System.getProperty("user.home"));  // Open CMD in user's home directory
	      }

	      // Path to the VBScript
	      File vbsScript = File.createTempFile("elevateCmd", ".vbs");

	      // Write the VBScript to request elevation and open CMD
	      try (BufferedWriter writer = new BufferedWriter(new FileWriter(vbsScript))) {
	          writer.write("Set shell = CreateObject(\"Shell.Application\")\n");
	          writer.write("shell.ShellExecute \"cmd.exe\", \"/k cd /d " + directory.getAbsolutePath() + "\", \"\", \"runas\", 1\n");
	      }

	      // Run the VBScript
	     // Runtime.getRuntime().exec("wscript " + vbsScript.getAbsolutePath());
	      // Use ProcessBuilder to run the VBScript
	      ProcessBuilder processBuilder = new ProcessBuilder("wscript", vbsScript.getAbsolutePath());
	      processBuilder.start();
	      // Optional: Delete the script after execution
	      vbsScript.deleteOnExit();

	  } catch (IOException e) {
	      JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error opening folder in Adminstrator CMD: " + e.getMessage());
	  }
	}
	
	//Report for bug
	 public static void WindowsReportBug() {
	        String gitHubIssuesUrl = "https://github.com/raghul-tech/NotepadXX/issues/new?template=bug_report.md";
	        try {
	            Desktop.getDesktop().browse(new URI(gitHubIssuesUrl));
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error: Unable to open the browser."+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	       //     e.printStackTrace(); // Print the stack trace for debugging
	        } catch (URISyntaxException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error: Invalid URL format.", "Error", JOptionPane.ERROR_MESSAGE);
	        //    e.printStackTrace(); // Print the stack trace for debugging
	        }
	    }
	
	 public static void WindowsDonate() {
	        String gitHubIssuesUrl = "https://buymeacoffee.com/raghultech";
	        try {
	            Desktop.getDesktop().browse(new URI(gitHubIssuesUrl));
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error: Unable to open the browser."+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	       //     e.printStackTrace(); // Print the stack trace for debugging
	        } catch (URISyntaxException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error: Invalid URL format.", "Error", JOptionPane.ERROR_MESSAGE);
	        //    e.printStackTrace(); // Print the stack trace for debugging
	        }
	    }
	
	 public static void WindowsDiscord() {
	        String gitHubIssuesUrl = "https://discord.com/invite/MQn4GT8CVp";
	        try {
	            Desktop.getDesktop().browse(new URI(gitHubIssuesUrl));
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error: Unable to open the browser."+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	       //     e.printStackTrace(); // Print the stack trace for debugging
	        } catch (URISyntaxException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error: Invalid URL format.", "Error", JOptionPane.ERROR_MESSAGE);
	        //    e.printStackTrace(); // Print the stack trace for debugging
	        }
	    }
	 
	 public static void WindowsEdgeBrowser(File currentFile) {
	        try {

	            // Get the file path and replace backslashes with forward slashes
	          //  String filePath = currentFile.getAbsolutePath().replace("\\", "/");

	            // Check if Desktop is supported (optional)
	            if (!Desktop.isDesktopSupported()) {
	                JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Microsoft Edge is not supported on your system.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            String[] command;

	            // Check if the current file is not null and exists
	            if (currentFile != null && currentFile.exists()) {
	                // Get the file path and replace backslashes with forward slashes
	                String filePath = currentFile.getAbsolutePath().replace("\\", "/");

	                // Command to open Microsoft Edge with the file path
	                command = new String[]{"cmd", "/c", "start msedge \"" + filePath + "\""};
	            } else {
	                // Command to open Microsoft Edge without any specific file (just the browser)
	                command = new String[]{"cmd", "/c", "start msedge"};
	            }
	            ProcessBuilder processBuilder = new ProcessBuilder(command);
	            processBuilder.start();

	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error opening the file in Microsoft Edge.", "Error", JOptionPane.ERROR_MESSAGE);
	           // e.printStackTrace(); // Print the stack trace for debugging
	        }
	    }
	 public static void WindowsChromeBrowser(File currentFile) {
	        try {

	            // Get the file path and replace backslashes with forward slashes
//	            String filePath = currentFile.getAbsolutePath().replace("\\", "/");

	            // Check if Desktop is supported (optional)
	            if (!Desktop.isDesktopSupported()) {
	                JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Google Chrome is not supported on your system.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            String[] command;

	            // Check if the current file is not null and exists
	            if (currentFile != null && currentFile.exists()) {
	                // Get the file path and replace backslashes with forward slashes
	                String filePath = currentFile.getAbsolutePath().replace("\\", "/");

	                // Command to open Microsoft Edge with the file path
	                command = new String[]{"cmd", "/c", "start chrome \"" + filePath + "\""};
	            } else {
	                // Command to open Microsoft Edge without any specific file (just the browser)
	                command = new String[]{"cmd", "/c", "start chrome"};
	            }

	            // Use ProcessBuilder to execute the command
	            ProcessBuilder processBuilder = new ProcessBuilder(command);
	            processBuilder.start();

	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error opening the file in Google Chrome.", "Error", JOptionPane.ERROR_MESSAGE);
	          //  e.printStackTrace(); // Print the stack trace for debugging
	        }
	    }
	 
	 public static void WindowsFirefoxBrowser(File currentFile) {
		    try {
		        // Check if Desktop is supported (optional)
		        if (!Desktop.isDesktopSupported()) {
		            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Mozilla Firefox is not supported on your system.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String[] command;

		        // Check if the current file is not null and exists
		        if (currentFile != null && currentFile.exists()) {
		            // Get the file path and replace backslashes with forward slashes
		            String filePath = currentFile.getAbsolutePath().replace("\\", "/");

		            // Command to open Mozilla Firefox with the file path
		            command = new String[]{"cmd", "/c", "start firefox \"" + filePath + "\""};
		        } else {
		            // Command to open Mozilla Firefox without any specific file (just the browser)
		            command = new String[]{"cmd", "/c", "start firefox"};
		        }

		        // Use ProcessBuilder to execute the command
		        ProcessBuilder processBuilder = new ProcessBuilder(command);
		        processBuilder.start();

		    } catch (IOException e) {
		        JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error opening the file in Mozilla Firefox.", "Error", JOptionPane.ERROR_MESSAGE);
		        // e.printStackTrace(); // Print the stack trace for debugging
		    }
		}
	 
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
	                        JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "You are using the latest version.", "Update Check", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                }
	            }else {

	            }
	        } catch (IOException| URISyntaxException  e) {
	           // e.printStackTrace();
	            if (manualCheck) {
	                // Show an error dialog ONLY if this is a manual check
	                JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error checking for updates.", "Update Check Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }

	    private static boolean isNewVersionAvailable(String latestVersion) {
	        return !currentVersion.equals(latestVersion);
	    }

	 private static void showUpdateDialog(UpdateInfo updateInfo) {

		    String changelogMessage = updateInfo.changelog != null ? "<br/><br/>Changelog:<br/>" + updateInfo.changelog : "";

		    String message = "A new version (" + updateInfo.latest_version + ") is available." + changelogMessage +
		                     "<br/>Do you want to download it now?";

		    int response = JOptionPane.showConfirmDialog(NotepadXXV1_2_1.getFrame(),
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
	                JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Failed to connect to the server. Response code: " + connection.getResponseCode(), "Connection Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            // Create a temporary file to store the downloaded version
	            File tempFile = File.createTempFile("NotepadXX", ".exe");

	            // Modern GUI for the download process
	            showModernInstallerGUI(tempFile, url, connection);
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error downloading the update: " + e.getMessage(), "Download Error", JOptionPane.ERROR_MESSAGE);
	          //  e.printStackTrace(); // Print stack trace for debugging
	        } finally {
	            if (connection != null) {
	                connection.disconnect(); // Ensure connection is closed
	            }
	        }
	    }

	    private static void showModernInstallerGUI(File newFile, URL downloadUrl, HttpURLConnection connection) {
	        JFrame installerFrame = new JFrame("Installing NotepadXX New Version");
	        installerFrame.setSize(500, 150);
	        installerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        installerFrame.setLocationRelativeTo(null);
	        installerFrame.setIconImage(new ImageIcon(new NotepadXXV1_2_1().getClass().getResource("/icons/NotepadXXLogo.png")).getImage());

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
	                statusLabel.setText("Download complete!");
	                finishButton.setEnabled(true); // Enable finish button when download is done
	            }
	        };

	        downloader.execute();
	    }

	    private static void runNewApplication(File programFilesDir) {
	        try {
	            // Define the path to the new application file
	            File newVersionFile = new File(programFilesDir.getAbsolutePath());

	            // Execute the new version of the application
	            ProcessBuilder processBuilder = new ProcessBuilder(newVersionFile.getAbsolutePath());
	            processBuilder.start();

	            // Exit the current application
	            System.exit(0);
	        } catch (IOException e) {
	          //  e.printStackTrace();
	            JOptionPane.showMessageDialog(NotepadXXV1_2_1.getFrame(), "Error running the new version of the application.", "Execution Error", JOptionPane.ERROR_MESSAGE);
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
