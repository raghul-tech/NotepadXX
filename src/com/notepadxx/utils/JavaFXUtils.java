package com.notepadxx.utils;

import java.awt.GraphicsEnvironment;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class JavaFXUtils {
		
	    private static boolean javaFXInitialized = false;
	    private static boolean javaFXAvailable = true;

	    public static synchronized void initializeJavaFX() {
	        if (!javaFXInitialized && javaFXAvailable) {
	            try {
	            	  new JFXPanel();
	            //    Platform.startup(() -> {
	                    Platform.setImplicitExit(false);
	                    javaFXInitialized = true;
	              //  });
	         
	            } catch (IllegalStateException e) {
	            //    Platform.runLater(() -> {});
	                javaFXInitialized = true;
	            } catch (Exception | Error e) {
	            	 javaFXAvailable = false; // mark JavaFX as not available
	                e.printStackTrace();
	            }
	        }
	     
	    }

	    public static synchronized boolean isJavaFXAvailable() {
	        if (!javaFXAvailable) return false;

	        try {
	            if (GraphicsEnvironment.isHeadless()) {
	                javaFXAvailable = false;
	                return false;
	            }

	            Class.forName("javafx.application.Platform");

	            if (!javaFXInitialized) {
	                initializeJavaFX();
	            }

	            return javaFXAvailable;
	        } catch (Throwable e) {
	            javaFXAvailable = false;
	            e.printStackTrace();
	            return false;
	        }
	    }
	


}
