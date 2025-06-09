package com.notepadxx.filewatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class WindowsFileWatcher implements Runnable {
    private final File file;
    private final RSyntaxTextArea textArea;
    private volatile boolean keepWatching = true;
    private volatile boolean isSaving = false;
    private static final long DEBOUNCE_DELAY = 1000; // 1 second debounce delay
    private long lastChangeTime = 0; // Last time a change was detected
    private WatchService watchService;
    private ScheduledExecutorService executorService;

    public WindowsFileWatcher(File file, RSyntaxTextArea textArea) {
        this.file = file;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path filePath = file.getParentFile().toPath();
          //  filePath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            filePath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE);
            
            // Using ScheduledExecutorService for debounce handling
            executorService = Executors.newSingleThreadScheduledExecutor();

            while (keepWatching) {
            	try {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                 //   if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    if ((kind == StandardWatchEventKinds.ENTRY_MODIFY || kind == StandardWatchEventKinds.ENTRY_CREATE
                            || kind == StandardWatchEventKinds.ENTRY_DELETE) && !isSaving) {
                        Path changed = filePath.resolve((Path) event.context());
                        if (changed.endsWith(file.getName())) {
                            long currentTime = System.currentTimeMillis();
                            if (!isSaving && currentTime - lastChangeTime > DEBOUNCE_DELAY) {
                                lastChangeTime = currentTime;
           
                                    scheduleFileReload();
                                
                            }
                        }
                    }
                }
                if (!key.reset()) break;
            }
            	  catch (ClosedWatchServiceException e) {
                     // System.out.println("WatchService was closed, stopping watcher.");
                      break; // Exit loop safely
                  }
            }
        }
        catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            stopWatching();
        }
    }
    
    // Schedule file reload with debounce
    private void scheduleFileReload() {
        if (executorService != null) {
            executorService.schedule(() -> SwingUtilities.invokeLater(this::reloadFile), 500, TimeUnit.MILLISECONDS);
        }
    }

    
    
    private void reloadFile() {
        if (!file.exists()) return;

        int scrollPos = textArea.getCaretPosition();
        double fileSizeInMB = file.length() / (1024.0 * 1024.0); // Convert bytes to MB
      /*  ReloadFile reload = new ReloadFile(file,textArea);
    	reload.loadFile( fileSizeInMB, scrollPos);  */
        // Using SwingWorker for background task
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
            	ReloadFile reload = new ReloadFile(file,textArea);
            	reload.loadFile( fileSizeInMB, scrollPos);         	
            //	new ReloadFile(file, textArea).loadFile(fileSizeInMB, scrollPos);
                return null;
            }

            @Override
            protected void done() {
                try {
                   // get(); // Ensures no exceptions occurred
                //	ReloadFile reload = new ReloadFile(file,textArea);
                	//reload.loadFile( fileSizeInMB, scrollPos);      
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public void setSaving(boolean saving) {
        this.isSaving = saving;
    }

    public void stopWatching() {
        keepWatching = false;
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
        try {
            if (watchService != null) {
                watchService.close();
            }
        } catch (IOException e) {
            // Handle WatchService closing error
        }
    }
}
