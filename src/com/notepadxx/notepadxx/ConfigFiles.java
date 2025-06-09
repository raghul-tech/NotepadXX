package com.notepadxx.notepadxx;

import java.io.File;


public class ConfigFiles {

    private static String APP_DIR;
    private static String CONFIG_FILE_THEME;
    private static String CONFIG_FILE_SIZE;
    private static String CONFIG_FILE_OPEN_FILES;
    private static String CONFIG_FILE_RECENT_CLOSED;
    private static String CONFIG_TOOL_TIP;
    private static String CONFIG_LANGUAGE;
    private static String CONFIG_CODE_ANALYSIS;
  

    static {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            initializeForWindows();
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            initializeForUnixLinuxAIX();
        }
    }

    private static void initializeForWindows() {
        APP_DIR = System.getProperty("user.home") + File.separator + "NotepadXX";
        CONFIG_FILE_THEME = APP_DIR + File.separator + "theme.properties";
        CONFIG_FILE_SIZE = APP_DIR + File.separator + "windowsize.properties";
        CONFIG_FILE_OPEN_FILES = APP_DIR + File.separator + "openfiles.properties";
        CONFIG_FILE_RECENT_CLOSED = APP_DIR + File.separator + "recentClosedFiles.properties";
        CONFIG_TOOL_TIP =  APP_DIR + File.separator + "ToolTip.properties";
        CONFIG_LANGUAGE =  APP_DIR + File.separator + "Language.properties";
        CONFIG_CODE_ANALYSIS =  APP_DIR + File.separator + "CodeAnalysis.properties";
       
    }

    private static void initializeForUnixLinuxAIX() {
        APP_DIR = System.getenv("XDG_CONFIG_HOME") != null
            ? System.getenv("XDG_CONFIG_HOME") + File.separator + "NotepadXX"
            : System.getProperty("user.home") + File.separator + ".config" + File.separator + "NotepadXX";

        CONFIG_FILE_THEME = APP_DIR + File.separator + "theme.properties";
        CONFIG_FILE_SIZE = APP_DIR + File.separator + "windowsize.properties";
        CONFIG_FILE_OPEN_FILES = APP_DIR + File.separator + "openfiles.properties";
        CONFIG_FILE_RECENT_CLOSED = APP_DIR + File.separator + "recentClosedFiles.properties";
        CONFIG_TOOL_TIP =  APP_DIR + File.separator + "ToolTip.properties";
        CONFIG_LANGUAGE =  APP_DIR + File.separator + "Language.properties";
        CONFIG_CODE_ANALYSIS =  APP_DIR + File.separator + "CodeAnalysis.properties";
      
       
    }


    // Getter methods for file paths
    public final static String getAppDir() {
        return APP_DIR;
    }


    public final static String getConfigFileTheme() {
        return CONFIG_FILE_THEME;
    }

    public final static String getConfigFileSize() {
        return CONFIG_FILE_SIZE;
    }

    public final static String getConfigFileOpenFiles() {
        return CONFIG_FILE_OPEN_FILES;
    }

    public final static String getConfigFileRecentClosed() {
        return CONFIG_FILE_RECENT_CLOSED;
    }

    public final static String getConfigToolTip() {
        return CONFIG_TOOL_TIP;
    }
    public final static String getConfigLanguage() {
    	return CONFIG_LANGUAGE;
    }
    public final static String getConfigCodeAnalysis() {
    	return CONFIG_CODE_ANALYSIS;
    }
   
    
}