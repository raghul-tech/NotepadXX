package com.notepadxx.open;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.notepadxx.notepadxx.Texteditor;
import com.notepadxx.utils.JavaFXUtils;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RenameFile {
	private Texteditor editor;
	
	public RenameFile(Texteditor editor) {
		this.editor = editor;
	}
	
	public void renameFileFX(File fileToRename) {
	    if (fileToRename == null || !fileToRename.exists()) {
	        return;
	    }

	    if (JavaFXUtils.isJavaFXAvailable()) {
	        try {
	            if (!Platform.isFxApplicationThread()) {
	                new JFXPanel(); // Initializes JavaFX
	            }

	            Platform.runLater(() -> {
	                try {
	                    FileChooser chooser = new FileChooser();
	                    chooser.setTitle("Rename File");
	                    Stage dummyStage = new Stage();
	                    dummyStage.getIcons().add(new Image(getClass().getResource("/icons/LogoX.png").toExternalForm()));

	                    dummyStage.initStyle(StageStyle.UTILITY);
	                    dummyStage.setWidth(1);
	                    dummyStage.setHeight(1);
	                    dummyStage.setX(-1000);
	                    dummyStage.setY(-1000);
	                    dummyStage.show(); // You must show it, even if off-screen
	                  
	                    chooser.setInitialDirectory(fileToRename.getParentFile());
	                    chooser.setInitialFileName(fileToRename.getName());

	                  //  File newFile = chooser.showSaveDialog(null);
	                    File newFile = chooser.showSaveDialog(dummyStage);
	                    dummyStage.close();
	                    if (newFile == null || fileToRename.getAbsolutePath().equals(newFile.getAbsolutePath())) {
	                        return; // User cancelled or chose same file
	                    }

	                    // Check if file exists and ask for overwrite
	                    if (newFile.exists()) {
	                        int overwrite = JOptionPane.showConfirmDialog(
	                                editor,
	                                "The file already exists. Overwrite?",
	                                "Confirm Overwrite",
	                                JOptionPane.YES_NO_OPTION
	                        );
	                        if (overwrite != JOptionPane.YES_OPTION) {
	                            return;
	                        }
	                    }

	                    Path sourcePath = fileToRename.toPath();
	                    Path targetPath = newFile.toPath();

	                    // Try renaming using NIO
	                    Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

	                    SwingUtilities.invokeLater(() -> {
	                        editor.setTabTitle(newFile.getName());
	                        editor.setCurrentFile(newFile);
	                        editor.updateTabTitle();
	                        editor.updateTitle(newFile.toString());
	                    });

	                } catch (IOException ioEx) {
	                    ioEx.printStackTrace();
	                    SwingUtilities.invokeLater(() ->
	                        JOptionPane.showMessageDialog(editor,
	                                "Failed to rename file: " + ioEx.getMessage(),
	                                "Rename Error",
	                                JOptionPane.ERROR_MESSAGE));
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                    SwingUtilities.invokeLater(() ->
	                        JOptionPane.showMessageDialog(editor,
	                                "Unexpected error: " + ex.getMessage(),
	                                "Error",
	                                JOptionPane.ERROR_MESSAGE));
	                }
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	            renameFileSwing(fileToRename); // fallback
	        }

	    } else {
	        renameFileSwing(fileToRename); // fallback
	    }
	}
	
	
	

	public void renameFileSwing(File fileToRename) {
	    if (fileToRename == null || !fileToRename.exists()) {
	        return;
	    }

	    // Create a hidden frame for dialogs
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setIconImage(new ImageIcon(RenameFile.class.getResource("/icons/NotepadXXLogo.png")).getImage());

	    JFileChooser fileChooser = new JFileChooser(fileToRename.getParentFile());
	    fileChooser.setDialogTitle("Rename File");
	    fileChooser.setSelectedFile(fileToRename); // Set current name

	    int result = fileChooser.showSaveDialog(frame);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File newFile = fileChooser.getSelectedFile();

	        if (newFile.getAbsolutePath().equals(fileToRename.getAbsolutePath())) {
	            frame.dispose();
	            return; // No change
	        }

	        Path sourcePath = fileToRename.toPath();
	        Path targetPath = newFile.toPath();

	        // Confirm overwrite if needed
	        if (Files.exists(targetPath)) {
	            int overwrite = JOptionPane.showConfirmDialog(
	                    frame,
	                    "The file already exists. Overwrite?",
	                    "Confirm Overwrite",
	                    JOptionPane.YES_NO_OPTION
	            );
	            if (overwrite != JOptionPane.YES_OPTION) {
	                frame.dispose();
	                return;
	            }
	        }

	        try {
	            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

	            SwingUtilities.invokeLater(() -> {
	                editor.setTabTitle(newFile.getName());
	                editor.setCurrentFile(newFile);
	                editor.updateTabTitle();
	                editor.updateTitle(newFile.toString());
	            });

	        } catch (IOException ioEx) {
	            ioEx.printStackTrace();
	            JOptionPane.showMessageDialog(frame,
	                    "Failed to rename file: " + ioEx.getMessage(),
	                    "Rename Error",
	                    JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(frame,
	                    "Unexpected error: " + ex.getMessage(),
	                    "Error",
	                    JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    frame.dispose();
	}


}
