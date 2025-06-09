package com.notepadxx.open;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.notepadxx.exit.CLOSE;
import com.notepadxx.notepadxx.NotepadXXV1_2_0;
import com.notepadxx.notepadxx.Texteditor;

public class loadFile {
    
      private Texteditor editor;
      private RSyntaxTextArea textArea;
     
        public loadFile(Texteditor editor,String tabTitle, RSyntaxTextArea textArea) {
            this.editor = editor;
            this.textArea = textArea;
        }

      
    
        protected void fileSizeToOpen(File openFile) {
            if (openFile == null || !openFile.exists()) {
               // JOptionPane.showMessageDialog(null, "File not found or invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        double fileSizeInMB = openFile.length() / (1024.0 * 1024.0); // Convert bytes to MB
      
      //  loadBigFile(openFile);	
                    if (fileSizeInMB < 5.0) {  
                        loadSmallFile(openFile);
                    } else if (fileSizeInMB < 20.0) {  
                        loadMediumFile(openFile);
                    } else if (fileSizeInMB < 60.0) {  
                    	 loadLargeFile(openFile); 	  
                    }else if (fileSizeInMB <90.0 ){
                    	loadVeryLargeFile(openFile);
                    	
                    }else {
                    	loadBigFile(openFile);
                    }
        
        }
        
   
        
        private void loadSmallFile(File newFile) {
            CompletableFuture.runAsync(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(newFile), StandardCharsets.UTF_8))) {

                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");  // Accumulate lines
                    }

                    // Use SwingWorker for UI update
                    SwingUtilities.invokeLater(() -> {
                        try {
                            textArea.append(content.toString()); // Set the complete content
                            textArea.setCaretPosition(0); // Set caret at the top
                            editor.updateAfterOpen(newFile, textArea); // Call for any additional updates after loading
                        } catch (OutOfMemoryError e) {
                            handleMemoryError(newFile); // Handle memory error
                        }
                    });
 
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(editor, "Error loading file: " + newFile.getName() + "\n" + e.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    });
                } catch (OutOfMemoryError e) {
                    SwingUtilities.invokeLater(() -> handleMemoryError(newFile)); // Handle memory issues if they occur
                }
            });
        }


    

        private void loadMediumFile(File file) {
            CompletableFuture.runAsync(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

                    StringBuilder content = new StringBuilder();
                    String line;
                    int lineCount = 0;

                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                        lineCount++;

                        // Update UI every 1000 lines to keep it responsive
                        if (lineCount % 1000 == 0) {
                            String partialContent = content.toString(); // Copy before clearing
                            SwingUtilities.invokeLater(() -> {
                            	
                            	try {
                                textArea.append(partialContent);
                                textArea.setCaretPosition(0);
                                editor.updateAfterOpen(file, textArea);
                            	} catch (OutOfMemoryError e) {
                                    handleMemoryError(file); // Handle memory error here
                                }
                            	});
                            content.setLength(0); // Clear buffer
                        }
                    }

                    // Append remaining content (if any)
                    String remainingContent = content.toString();
                    if (!remainingContent.isEmpty()) {
                        SwingUtilities.invokeLater(() -> {
                        	try {
                            textArea.append(remainingContent);
                            textArea.setCaretPosition(0);
                            editor.updateAfterOpen(file, textArea);
                        	} catch (OutOfMemoryError e) {
                                handleMemoryError(file); // Handle memory error here
                            }
                        });
                    }

                } catch (OutOfMemoryError e) {
                    handleMemoryError(file);
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                        editor, "Error loading file: " + file.getName() + "\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE
                    ));
                }
            });
        }

    
        private void loadLargeFile(File newFile) {
            CompletableFuture.runAsync(() -> {
                Charset charset = StandardCharsets.UTF_8;  // Fixed charset usage
                CharsetDecoder decoder = charset.newDecoder();
                
                try (RandomAccessFile raf = new RandomAccessFile(newFile, "r");
                     FileChannel fileChannel = raf.getChannel()) {

                    ByteBuffer buffer = ByteBuffer.allocate(65536); // 64KB buffer (more efficient)
                    StringBuilder content = new StringBuilder(65536); // Initial capacity avoids resizing

                    int linesRead = 0;
                    while (fileChannel.read(buffer) > 0) {
                        buffer.flip();
                        
                        // Decode using a reusable CharBuffer
                        CharBuffer charBuffer = decoder.decode(buffer);
                        content.append(charBuffer);

                        buffer.clear();
                        linesRead++;

                        // Batch UI updates (every 1000 lines)
                        if (linesRead % 1000 == 0) {
                            String partialContent = content.toString();
                            SwingUtilities.invokeLater(() ->{
                            	try {
                            textArea.append(partialContent);
                            	 } catch (OutOfMemoryError e) {
                                     handleMemoryError(newFile);
                                 }
                            });
                            
                            content.setLength(0); // Clear buffer to save memory
                        }
                    }

                    // Append remaining content
                    if (content.length() > 0) {
                        String remainingContent = content.toString();
                        SwingUtilities.invokeLater(() -> {
                        	try {
                            textArea.append(remainingContent);
                            textArea.setCaretPosition(0); 
                            editor.updateAfterOpen(newFile, textArea);
                        	 } catch (OutOfMemoryError e) {
                                 handleMemoryError(newFile);
                             }
                        });
                    }

                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                        editor, "Error loading file: " + newFile.getName() + "\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE));
                } catch (OutOfMemoryError e) {
                    handleMemoryError(newFile);
                }
            });
        }


       
        private void loadVeryLargeFile(File file) {
            final int BUFFER_SIZE = 32 * 1024; // 32KB buffer

            CompletableFuture.runAsync(() -> {
                try (FileInputStream fis = new FileInputStream(file);
                     FileChannel fileChannel = fis.getChannel();
                     InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(isr, BUFFER_SIZE)) {

                    char[] buffer = new char[BUFFER_SIZE];
                    int charsRead;
                    StringBuilder content = new StringBuilder(); // Use small buffer for UI updates
                    int lineCount = 0; // Track progress

                    while ((charsRead = reader.read(buffer)) != -1) {
                        content.append(buffer, 0, charsRead);
                        lineCount++;

                        // Update UI in chunks to prevent UI freezing
                        if (lineCount % 100 == 0) { // Update every 100 reads
                            String partialContent = content.toString();
                            SwingUtilities.invokeLater(() -> {
                            	try {
                                textArea.append(partialContent);
                                textArea.setCaretPosition(0);
                            	} catch (OutOfMemoryError e) {
                                    handleMemoryError(file);
                                }
                            	});
                            content.setLength(0); // Clear buffer to reduce memory usage
                        }
                    }

                    // Append remaining content (if any)
                    if (content.length() > 0) {
                        SwingUtilities.invokeLater(() -> {
                        	try {
                            textArea.append(content.toString());
                            textArea.setCaretPosition(0);
                            editor.updateAfterOpen(file, textArea);
                        	} catch (OutOfMemoryError e) {
                                handleMemoryError(file);
                            }
                        });
                    }

                } catch (IOException e) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                            editor, "Error opening file: " + file.getName() + "\n" + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE));
                } catch (OutOfMemoryError e) {
                    handleMemoryError(file);
                }
            });
        }


        private void loadBigFile(File file) {
        //	 CompletableFuture.runAsync(() -> {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r");
                 FileChannel fileChannel = raf.getChannel()) {

                long fileSize = fileChannel.size();
                long maxLoadSize = Math.min(fileSize, Integer.MAX_VALUE); // Prevent overflow

                // Use Memory Mapping for fast reading
                MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, maxLoadSize);
                byte[] byteArray = new byte[(int) maxLoadSize];
                buffer.get(byteArray);

                String content = new String(byteArray, StandardCharsets.UTF_8); // Convert bytes to string

              /*  SwingUtilities.invokeLater(() -> {
                	 try {
                		 textArea.setText(content);
                         textArea.setCaretPosition(0);
                         editor.updateAfterOpen(file, textArea);
                     } catch (OutOfMemoryError e) {
                         handleMemoryError(file); // Handle memory error here
                     }
                });*/
                
                // Use SwingWorker for UI updates
                new SwingWorker<Void, String>() {
                    @Override
                    protected Void doInBackground() {
                        publish(content);
                        return null;
                    }

                    @Override
                    protected void process(List<String> chunks) {
                        for (String chunk : chunks) {
                        	try {
                            textArea.append(chunk); // Append efficiently
                        	} catch (OutOfMemoryError e) {
                                handleMemoryError(file);
                               
                            }
                        }
                        textArea.setCaretPosition(0);
                    }

                    @Override
                    protected void done() {
                   /* 	try {
                            textArea.append(content);
                        	} catch (OutOfMemoryError e) {
                                handleMemoryError(file);
                               
                            }
                        textArea.setCaretPosition(0);*/
                        editor.updateAfterOpen(file, textArea);
                    }
                }.execute();

            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                        editor, "Error opening file: " + file.getName() + "\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE));
            } catch (OutOfMemoryError e) {
                SwingUtilities.invokeLater(() -> handleMemoryError(file));
            }
        //	 });
        }

   
        private boolean memoryErrorShown = false; // Prevent duplicate dialogs

        private void handleMemoryError(File file) {
            if (memoryErrorShown) return; // Exit if dialog is already shown
            memoryErrorShown = true; // Set flag to true

            SwingUtilities.invokeLater(() -> {
                int choice = JOptionPane.showOptionDialog(editor,
                       "*"+ file.getName()+"* is too large to load completely.\nDo you want to load in large file mode or close the tab?",
                        "Memory Error",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, new String[]{"Load Large File Mode", "Close Tab"}, "Load Large File Mode");

                if (choice == JOptionPane.YES_OPTION) {
                	System.gc();
                	
                	  SwingUtilities.invokeLater(() -> {
                          NotepadXXV1_2_0.openOutOfMemoryErrorTab(file);
                         
                          CLOSE.closeTab(editor);
                       //   new FileWatcher(file,textArea).stopWatching();
                          memoryErrorShown = false;
                      });
                } else {
                	 SwingUtilities.invokeLater(() -> {
                         CLOSE.closeTab(editor);
                         System.gc();
                         memoryErrorShown = false;
                     });           
                    		}

             //   memoryErrorShown = false; // Reset flag after dialog is handled
            });
        }



}




