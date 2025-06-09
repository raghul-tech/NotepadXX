package com.notepadxx.flatlaf.theme;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.fife.ui.rtextarea.Gutter;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.notepadxx.notepadxx.ConfigFiles;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;

public class Themes {
	private static  Font defaultFont = new Font("Monospaced", Font.PLAIN, 18);
	private static  String currentTheme;
	//private static Font defaultFont = new Font("Courier New", Font.PLAIN, 18);
	 
	 private static Texteditor texteditor;

	// private  static NotepadXXV1_2_0 notepad = new NotepadXXV1_2_0();		
		
	/*	public Themes(Texteditor editor) {
	        texteditor = editor; 
	    }*/
	 
	 public static void init(Texteditor editor) {
		texteditor = editor; 
	 }


	public static synchronized void applyTheme(String theme) {
	        try {
	            if ("Dark".equals(theme)) {
	                UIManager.setLookAndFeel(new FlatDarkLaf());
	            }
	            else if ("Darcula".equals(theme)) {
	           	 UIManager.setLookAndFeel(new FlatDarculaLaf());
	           }
	            else if ("MacDark".equals(theme)) {
	            	 UIManager.setLookAndFeel(new FlatMacDarkLaf());
	            }
	            else if ("Light".equals(theme)) {
	            	 UIManager.setLookAndFeel(new FlatLightLaf());
	            }
	            else if ("Classic".equals(theme)) {
	           	 UIManager.setLookAndFeel(new FlatIntelliJLaf());
	           }
	            else if("MacLight".equals(theme)){
	            	 UIManager.setLookAndFeel(new FlatMacLightLaf());
	            }else {
	            	 UIManager.setLookAndFeel(new FlatLightLaf());
	            }
	            saveThemePreference(theme);
	           currentTheme = theme;
	            SwingUtilities.invokeLater(() -> {
	                if (NotepadXXV1_2_0.getFrame() != null) {
	                     SwingUtilities.updateComponentTreeUI(NotepadXXV1_2_0.getFrame());
	           
	                    if(NotepadXXV1_2_0.texteditor() != null) {
	               
	                    	applyFont(theme);
	                    }
	                        }
	            });
	        } catch (Exception e) {
	           // e.printStackTrace();
	            JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(),"Error while changing theme ","Error",JOptionPane.ERROR_MESSAGE);
	        }
	    }

	// Get the current theme (retrieve the theme)
	    public static  String getTheme() {
	       /* if (currentTheme == null) {
	         currentTheme = notepad.loadThemePreference();
	        }*/
	        return currentTheme;

	    }

	 public synchronized static  void applyFont(String theme) {

		        if ("Dark".equalsIgnoreCase(theme)) {
		        	texteditor.getTextArea().setBackground(new Color(50, 50, 50)); // A light black/dark grey color
		        	texteditor.getTextArea().setForeground(Color.WHITE);
		        	texteditor.getTextArea().setCurrentLineHighlightColor(Color.DARK_GRAY); // Change current line highlight color
		        	texteditor.getTextArea().setCaretColor(Color.LIGHT_GRAY);
		        	texteditor.getTextArea().setFont(defaultFont);
		        	texteditor.getTextArea().setSelectionColor(new Color(70,70,70));
		            // Customize the scroll pane gutter for the dark theme
		            Gutter gutter =  texteditor.getScrollPane().getGutter();
		            gutter.setLineNumberColor(Color.LIGHT_GRAY);  // Light line number color for dark theme contrast
		            gutter.setLineNumberFont(defaultFont);
		            gutter.setBackground(new Color(50,50,50));
		            gutter.setBorder(new EmptyBorder(10, 10, 10, 10));

		            texteditor.getScrollPane().getViewport().setBackground(Color.DARK_GRAY);
		            texteditor.getScrollPane().setBackground(Color.DARK_GRAY);

		        }
		        else if("Darcula".equals(theme)) {
		        	texteditor.getTextArea().setBackground(Color.BLACK); // A light black/dark grey color
		        	texteditor.getTextArea().setForeground(Color.WHITE);
		        	texteditor.getTextArea().setCurrentLineHighlightColor(new Color(40, 40, 40)); // Change current line highlight color
		        	texteditor.getTextArea().setCaretColor(Color.WHITE);
		        	texteditor.getTextArea().setFont(defaultFont);
		        	texteditor.getTextArea().setSelectionColor(new Color(70,70,70));
		            // Customize the scroll pane gutter for the dark theme
		            Gutter gutter =  texteditor.getScrollPane().getGutter();
		            gutter.setLineNumberColor(Color.WHITE);  // Light line number color for dark theme contrast
		            gutter.setLineNumberFont(defaultFont);
		            gutter.setBackground(Color.BLACK);
		            gutter.setBorder(new EmptyBorder(10, 10, 10, 10));  // Add padding to the gutter (line number area)

		            // Make the background of the viewport and scroll pane seamless
		            texteditor.getScrollPane().getViewport().setBackground(Color.DARK_GRAY);
		            texteditor.getScrollPane().setBackground(Color.DARK_GRAY);
		        }
		        else if("MacDark".equals(theme)) {
		        	texteditor.getTextArea().setBackground(texteditor.getBackground()); // A light black/dark grey color
		        	texteditor.getTextArea().setForeground(Color.WHITE);
		        	texteditor.getTextArea().setCurrentLineHighlightColor(new Color(40, 40, 40)); // Change current line highlight color
		        	texteditor.getTextArea().setCaretColor(Color.white);
		        	texteditor.getTextArea().setFont(defaultFont);
		        	texteditor.getTextArea().setSelectionColor(new Color(70,70,70));
		            // Customize the scroll pane gutter for the dark theme
		            Gutter gutter =  texteditor.getScrollPane().getGutter();
		            gutter.setLineNumberColor(Color.WHITE);  // Light line number color for dark theme contrast
		            gutter.setLineNumberFont(defaultFont);
		          //  gutter.setBackground(Color.BLACK);
		            gutter.setBackground(texteditor.getBackground());
		            gutter.setBorder(new EmptyBorder(10, 10, 10, 10));  // Add padding to the gutter (line number area)

		            // Make the background of the viewport and scroll pane seamless
		            texteditor.getScrollPane().getViewport().setBackground(Color.DARK_GRAY);
		            texteditor.getScrollPane().setBackground(Color.DARK_GRAY);
		        }

		        else if ("Light".equalsIgnoreCase(theme)){
		        	texteditor.getTextArea().setBackground(Color.WHITE);
		            //texteditor.getTextArea().setBackground(texteditor.getBackground());
		        	texteditor.getTextArea().setForeground(Color.BLACK);
		        	texteditor.getTextArea().setCurrentLineHighlightColor(new Color(240, 240, 240)); // Change current line highlight color
		        	texteditor.getTextArea().setCaretColor(Color.BLACK); // Change caret color
		        	texteditor.getTextArea().setFont(defaultFont);
		        	texteditor.getTextArea().setSelectionColor(new Color(200,200,200));
		            // Customize the scroll pane gutter for the light theme
		            Gutter gutter =  texteditor.getScrollPane().getGutter();
		            gutter.setLineNumberColor(Color.GRAY);  // Darker line number color for light theme
		            gutter.setLineNumberFont(defaultFont);
		            gutter.setBackground(Color.WHITE);
		            //gutter.setBackground(new Color(245, 245, 245));

		            // Add padding around line numbers
		            gutter.setBorder(new EmptyBorder(10, 10, 10, 10));  // Add padding to the gutter (line number area)

		            // Make the background of the viewport and scroll pane seamless
		            texteditor.getScrollPane().getViewport().setBackground(Color.WHITE);
		            texteditor.getScrollPane().setBackground(Color.WHITE);
		        }
		        else if("Classic".equals(theme)) {
		        	//texteditor.getTextArea().setBackground(new Color(255,250,250));
		        	texteditor.getTextArea().setBackground(texteditor.getBackground());// A light black/dark grey color
		        	texteditor.getTextArea().setForeground(Color.BLACK);
		        	texteditor.getTextArea().setCurrentLineHighlightColor(new Color(220, 220, 220)); // Change current line highlight color
		        	texteditor.getTextArea().setCaretColor(Color.BLACK);
		        	texteditor.getTextArea().setFont(defaultFont);
		        	texteditor.getTextArea().setSelectionColor(new Color(200,200,200));
		            // Customize the scroll pane gutter for the dark theme
		            Gutter gutter =  texteditor.getScrollPane().getGutter();
		            gutter.setLineNumberColor(Color.GRAY);  // Light line number color for dark theme contrast
		            gutter.setLineNumberFont(defaultFont);
		           // gutter.setBackground(new Color(255,255,255));
		            gutter.setBackground(texteditor.getBackground());
		            gutter.setBorder(new EmptyBorder(10, 10, 10, 10));  // Add padding to the gutter (line number area)

		            // Make the background of the viewport and scroll pane seamless
		            texteditor.getScrollPane().getViewport().setBackground(Color.DARK_GRAY);
		            texteditor.getScrollPane().setBackground(Color.DARK_GRAY);
		        }else if("MacLight".equals(theme)) {
		        	texteditor.getTextArea().setBackground(Color.WHITE);
		        	// texteditor.getTextArea().setBackground(texteditor.getBackground());
		        	texteditor.getTextArea().setForeground(Color.BLACK);
		        	texteditor.getTextArea().setCurrentLineHighlightColor(new Color(240, 240, 240)); // Change current line highlight color
		        	texteditor.getTextArea().setCaretColor(Color.BLACK); // Change caret color
		        	texteditor.getTextArea().setFont(defaultFont);
		        	texteditor.getTextArea().setSelectionColor(new Color(200,200,200));
		            // Customize the scroll pane gutter for the light theme
		            Gutter gutter =  texteditor.getScrollPane().getGutter();
		            gutter.setLineNumberColor(Color.GRAY);  // Darker line number color for light theme
		            gutter.setLineNumberFont(defaultFont);
		            gutter.setBackground(Color.WHITE);
		           // gutter.setBackground(getBackground());

		            // Add padding around line numbers
		            gutter.setBorder(new EmptyBorder(10, 10, 10, 10));  // Add padding to the gutter (line number area)
		            // Make the background of the viewport and scroll pane seamless
		            texteditor.getScrollPane().getViewport().setBackground(Color.WHITE);
		            texteditor.getScrollPane().setBackground(Color.WHITE);
		        }else {
		        	texteditor.getTextArea().setBackground(Color.WHITE);
		            //texteditor.getTextArea().setBackground(texteditor.getBackground());
		        	texteditor.getTextArea().setForeground(Color.BLACK);
		        	texteditor.getTextArea().setCurrentLineHighlightColor(new Color(240, 240, 240)); // Change current line highlight color
		        	texteditor.getTextArea().setCaretColor(Color.BLACK); // Change caret color
		        	texteditor.getTextArea().setFont(defaultFont);
		        	texteditor.getTextArea().setSelectionColor(new Color(200,200,200));
		            // Customize the scroll pane gutter for the light theme
		            Gutter gutter =  texteditor.getScrollPane().getGutter();
		            gutter.setLineNumberColor(Color.GRAY);  // Darker line number color for light theme
		            gutter.setLineNumberFont(defaultFont);
		            gutter.setBackground(Color.WHITE);
		            //gutter.setBackground(new Color(245, 245, 245));

		            // Add padding around line numbers
		            gutter.setBorder(new EmptyBorder(10, 10, 10, 10));  // Add padding to the gutter (line number area)

		            // Make the background of the viewport and scroll pane seamless
		            texteditor.getScrollPane().getViewport().setBackground(Color.WHITE);
		            texteditor.getScrollPane().setBackground(Color.WHITE);
		        }

		        // Remove the border to ensure a seamless look between the gutter and text area
		        texteditor.getScrollPane().setBorder(BorderFactory.createEmptyBorder());  // Remove border for seamless look
		}




	private  synchronized static void saveThemePreference(String theme) {
	        try (FileOutputStream output = new FileOutputStream(ConfigFiles.getConfigFileTheme())) {
	            Properties prop = new Properties();
	            prop.setProperty("theme", theme);
	            prop.store(output, "NotepadXX V1.2.0 Theme Configuration");
	        } catch (IOException e) {
	           // e.printStackTrace();
	        	JOptionPane.showMessageDialog(NotepadXXV1_2_0.getFrame(), "An error occurred while saving your theme preference.\n Please try again.", "Theme not saved", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }








}
