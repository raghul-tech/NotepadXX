package com.notepadxx.filewatcher;

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

import com.notepadxx.notepadxx.NotepadXXV1_2_1;

public class ReloadFile {

	private File file;
	private RSyntaxTextArea textArea;
	
	    public ReloadFile(File file,RSyntaxTextArea textArea){
	    	  this.file = file;
	    	  this.textArea = textArea;
	      }
	
	
	protected void loadFile(double fileSizeInMB , int scrollPos) {
              textArea.setText(null);
		 if (fileSizeInMB < 5.0) {
          loadSmallFile(file,scrollPos);
      } else if (fileSizeInMB < 30.0) {  
          loadMediumFile(file,scrollPos);
      } else if (fileSizeInMB < 60.0) {  
          loadLargeFile(file,scrollPos);
      } else if(fileSizeInMB < 90.0) {
          loadVeryLargeFile(file,scrollPos);
      }else {
    	  loadBigFile(file,scrollPos);
      }
		// System.out.println("FILE HAS BEEN RELOADED "+ file.getName()+" " + fileSizeInMB);
	}
	
	 private void loadSmallFile(File newFile,int scrollPos) {
     	CompletableFuture.runAsync(() -> {
             try (BufferedReader reader = new BufferedReader(
                     new InputStreamReader(new FileInputStream(newFile), StandardCharsets.UTF_8))) {

                 StringBuilder content = new StringBuilder();
                 String line;
                 while ((line = reader.readLine()) != null) {
                     content.append(line).append("\n");
                 }

                 // Use SwingWorker for UI update
                 SwingUtilities.invokeLater(() -> {
                     try {
                         textArea.setText(content.toString()); // Set the complete content
                         textArea.setCaretPosition(Math.min(scrollPos, content.length()));
                        
                     } catch (OutOfMemoryError e) {
                         handleMemoryError(newFile,scrollPos); // Handle memory error
                     }
                 });
             } catch (IOException e) {
                 SwingUtilities.invokeLater(() -> {
                     JOptionPane.showMessageDialog(null, "Error loading file: " + newFile.getName() + 
                             "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 });
             } catch (OutOfMemoryError e) {
                 SwingUtilities.invokeLater(() -> {
                     handleMemoryError(newFile,scrollPos);
                 });
             }
         });
     }

	 
	 private void loadMediumFile(File file,int scrollPos) {
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
                         } catch (OutOfMemoryError e) {
                             handleMemoryError(file,scrollPos); // Handle memory error here
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
                    	 textArea.setCaretPosition(Math.min(scrollPos, content.length()));
                    	 } catch (OutOfMemoryError e) {
                             handleMemoryError(file,scrollPos); // Handle memory error here
                         }
                    	 });
                 }

             } catch (OutOfMemoryError e) {
                 handleMemoryError(file,scrollPos);
             } catch (IOException e) {
                 SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                     null, "Error loading file: " + file.getName() + "\n" + e.getMessage(),
                     "Error", JOptionPane.ERROR_MESSAGE
                 ));
             }
         });
     }

	 private void loadLargeFile(File newFile, int scrollPos) {
         CompletableFuture.runAsync(() -> {
             Charset detectedCharset = StandardCharsets.UTF_8;
             CharsetDecoder decoder = detectedCharset.newDecoder();

             try (RandomAccessFile raf = new RandomAccessFile(newFile, "r");
                  FileChannel fileChannel = raf.getChannel()) {

                 StringBuilder content = new StringBuilder();
                 ByteBuffer buffer = ByteBuffer.allocate(8192);
                 CharBuffer charBuffer = CharBuffer.allocate(8192);
                 int lineCount = 0;

                 while (fileChannel.read(buffer) > 0) {
                     buffer.flip(); // Prepare for reading
                     decoder.decode(buffer, charBuffer, false); // Decode bytes into characters
                     charBuffer.flip(); // Prepare CharBuffer for reading
                     content.append(charBuffer.toString()); // Append decoded text
                     buffer.clear(); // Clear buffers for next iteration
                     charBuffer.clear();
                     lineCount++;

                     // Update UI in chunks to prevent freezing
                     if (lineCount % 500 == 0) {
                         String partialContent = content.toString();
                         SwingUtilities.invokeLater(() -> {
                        	 try {
                                 textArea.append(partialContent);
                                 	 } catch (OutOfMemoryError e) {
                                          handleMemoryError(newFile,scrollPos);
                                      }
                         });
                         content.setLength(0); // Clear buffer to reduce memory usage
                     }
                 }

              // Append remaining content (if any)
                 String remainingContent = content.toString();
                 if (!remainingContent.isEmpty()) {
                     SwingUtilities.invokeLater(() -> {
                    		try {
                                textArea.append(remainingContent);
                                textArea.setCaretPosition(Math.min(scrollPos, content.length()));
                              
                            	 } catch (OutOfMemoryError e) {
                                     handleMemoryError(newFile,scrollPos);
                                 }
                     });
                 }

             } catch (IOException e) {
            	 SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                         null, "Error loading file: " + file.getName() + "\n" + e.getMessage(),
                         "Error", JOptionPane.ERROR_MESSAGE
                     ));
             }catch (OutOfMemoryError e) { 
                 handleMemoryError(newFile,scrollPos); // Handle memory issue
             }
         
         });
     }

	 private void loadVeryLargeFile(File file, int scrollPos) {
	        final int BUFFER_SIZE = 32 * 1024; // 32KB buffer

	            CompletableFuture.runAsync(() -> {
	                long fileSize = file.length();
	                try (FileInputStream fis = new FileInputStream(file);
	                     FileChannel fileChannel = fis.getChannel();
	                     InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	                     BufferedReader reader = new BufferedReader(isr, BUFFER_SIZE)) {

	                    // Use StringBuilder for efficient string manipulation
	                    StringBuilder content = new StringBuilder((int) Math.min(fileSize, Integer.MAX_VALUE / 2));
	                    int lineCount = 0;
	                    char[] buffer = new char[BUFFER_SIZE];
	                    int charsRead;
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
	                                    handleMemoryError(file,scrollPos);
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
	                            textArea.setCaretPosition(Math.min(scrollPos, content.length()));
	                        	} catch (OutOfMemoryError e) {
	                                handleMemoryError(file,scrollPos);
	                            }
	                        });
	                    }

	                } catch (IOException e) {
	                	 SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
	         	                null, "Error opening file: " + file.getName() + "\n" + e.getMessage(),
	         	                "Error", JOptionPane.ERROR_MESSAGE));
	                }catch (OutOfMemoryError e) { 
	                    handleMemoryError(file,scrollPos); // Handle memory issue
	                }
	            });
	        }
	 private void loadBigFile(File file, int scrollPos) {
         CompletableFuture.runAsync(() -> {
             try (RandomAccessFile raf = new RandomAccessFile(file, "r");
                  FileChannel fileChannel = raf.getChannel()) {

                 long fileSize = fileChannel.size();
                 long maxLoadSize = Math.min(fileSize, Integer.MAX_VALUE); // Prevent overflow

                 // Use Memory Mapping for large files
                 MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, maxLoadSize);
                 byte[] byteArray = new byte[(int) maxLoadSize];
                 buffer.get(byteArray);

                 // Convert bytes to string
                 String content = new String(byteArray, StandardCharsets.UTF_8);

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
	                                handleMemoryError(file,scrollPos);
	                               
	                            }
	                        }
	                        textArea.setCaretPosition(Math.min(scrollPos, content.length()));
	                    }
	                   
	                   
	                }.execute();
	                    
                 
                 
             /*    SwingUtilities.invokeLater(() -> {
                 	 try {
                          updateUI(content,scrollPos);
                      } catch (OutOfMemoryError e) {
                          handleMemoryError(file,scrollPos); // Handle memory error here
                      }
                 });*/

             } catch (IOException e) {
                 SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                         null, "Error opening file: " + file.getName() + "\n" + e.getMessage(),
                         "Error", JOptionPane.ERROR_MESSAGE));
             } catch (OutOfMemoryError e) {
                 SwingUtilities.invokeLater(() -> handleMemoryError(file,scrollPos));
             }
             catch (Throwable e) { // ✅ Catch any other unexpected errors (last resort)
                 SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                         null, "Unexpected error: " + e.getMessage(),
                         "Critical Error", JOptionPane.ERROR_MESSAGE));
             }
         });
     }
	 

	
private boolean memoryErrorShown = false; // Prevent duplicate dialogs
private void handleMemoryError(File file,int scrollPos) {
	 if (memoryErrorShown) return; // Exit if dialog is already shown
     memoryErrorShown = true; // Set flag to true
    SwingUtilities.invokeLater(() -> {
     //   disableMemoryIntensiveFeatures();
    NotepadXXV1_2_1.getTabbedPane().remove(NotepadXXV1_2_1.texteditor());
    	NotepadXXV1_2_1.checkAndOpenDefaultTab();
     //  JOptionPane.showMessageDialog(null, "File too large to load completely. Partial content loaded.", "Error", JOptionPane.ERROR_MESSAGE);
    //   System.out.println("MEMORY OUT ERROR OCCURED");
     //  loadVeryLargeFile(file,scrollPos);
      //new FileWatcher(file,textArea).stopWatching();
        //editor.updateAfterOpen(file, textArea);
    });
   // memoryErrorShown = false; 
}

}
