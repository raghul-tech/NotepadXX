import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FileBrowser extends JPanel implements ActionListener {			
		JLabel label = new JLabel(" File List: ");
		JButton newFile = new JButton(" New File ");
		JButton open = new JButton(" Open ");
		JButton delete = new JButton(" Delete ");
		ButtonGroup bg;
		File directory;
		File dictionary;
		CardLayout cl;
		JPanel fileList;
		JPanel panel = new JPanel();
		JScrollPane scrollPane;
		JButton gotoDir;
		
	    FileBrowser(){
	    	setLayout(new CardLayout());
	    	
	    	dictionary = new File("NotePadXX-Files");
	    	if(!dictionary.exists()) {
	    		dictionary.mkdir();
	    	}
	    	directory = dictionary;
	    
	    	
	    	JPanel buttonPanel = new JPanel(new GridLayout(1,3));
	    	buttonPanel.add(open);
	    	buttonPanel.add(newFile);
	    	buttonPanel.add(delete);
	    	newFile.addActionListener(this);
		    open.addActionListener(this);
		    delete.addActionListener(this);
	    		    	
	        JPanel fileListPanel = new JPanel(new GridLayout(0,1));
	    	fileListPanel.add(buttonPanel, BorderLayout.CENTER);
			fileListPanel.add(label,BorderLayout.NORTH);
			bg = new ButtonGroup();
			
	    	updateFileList();
		
			panel.add(fileListPanel);
			add(panel,"fileList");
		   
	         panel.setBackground(Color.LIGHT_GRAY);
	         
			 cl = (CardLayout) (getLayout());
			 
			 System.out.println("File Browser is Executed Successfully ");
	    }
	    
	    public void updateFileList() {
	    	  
				panel.removeAll();
			
				panel.add(label,BorderLayout.WEST);
				 fileList = new JPanel(new GridLayout(0,1));
				   bg= new ButtonGroup();
		        if(!directory.equals(dictionary)) {
		        	gotoDir = new JButton("..(Go to Parent Directory)");
		        	gotoDir.addActionListener(this);
		        	fileList.add(gotoDir);
		        }
		       
		        File[] files = directory.listFiles();
		        if(files != null) {
			         for(File file : files) {
			           	JRadioButton radio = new JRadioButton(file.getName() + (file.isDirectory() ? "  [Folder]":""));
			          	radio.setActionCommand(file.getAbsolutePath());
			            bg.add(radio);
				        fileList.add(radio);
			}
		        }
		        scrollPane = new JScrollPane(fileList);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			    scrollPane.setPreferredSize(new java.awt.Dimension(350,300));
				panel.add(scrollPane,BorderLayout.CENTER);
	    	    
			    panel.add(open);
				panel.add(newFile);
				panel.add(delete);
				
			    panel.revalidate();
				panel.repaint();
				 
				 System.out.println("File Browser updateFileList is Executed Successfully ");   	
	    }
	    
	    	    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == open) {
			String selectedFile = bg.getSelection() != null ? bg.getSelection().getActionCommand() : null;
			if(selectedFile != null) {
				if(selectedFile.equals("parentDir")) {
					directory = directory.getParentFile();
					updateFileList();
					
				}else {
				File selected = new File(selectedFile);
				if(selected.isDirectory()) {
					directory = selected;
					updateFileList();
				}
				else {
					System.out.println("Opening file: "+selectedFile);
					try {
						add(new Editor(selected.getAbsolutePath()),"editor");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cl.show(this, "editor");
				}
			}
			}
			System.out.println("Open is Executed Successfully: "+selectedFile);
		}
		if(e.getSource() == newFile) {
			JFileChooser fileChooser = new JFileChooser(directory);
			fileChooser.setDialogTitle("Specify a file to create");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(true);
			 String[] extensions = {"text", "html", "css", "javascript", "python", "java"};
	            for (String ext : extensions) {
	                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(ext.toUpperCase() + " files", ext));
	            }
			
			int userSelect = fileChooser.showSaveDialog(this);
			if(userSelect == JFileChooser.APPROVE_OPTION) {
				File fileToCreate = fileChooser.getSelectedFile();
				if(!fileToCreate.getName().contains(".")) {
					fileToCreate = new File(fileToCreate.getAbsolutePath()+".txt");	
				}
				if(fileToCreate.exists()) {
					JOptionPane.showMessageDialog(this, "File Already exists!","Error",JOptionPane.ERROR_MESSAGE);
					System.out.println("FILE ALREADY EXIST");
				}
				else{
					try {
						fileToCreate.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
					try {
						add(new Editor(fileToCreate.getAbsolutePath()),"editor");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
					}
					cl.show(this,"editor");
					updateFileList();	
					System.out.println("New File  is Executed Successfully: "+fileToCreate);
					
				}
						}
			
		}
		if(e.getSource() == delete) {
			String selectedFile = bg.getSelection() != null ? bg.getSelection().getActionCommand():null;
			if(selectedFile != null && !selectedFile.equals("parentDir")) {
				int option = JOptionPane.showConfirmDialog(this,"Are you Sure you want to delete: " + selectedFile + "?"," Delete File ",JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					File fileToDelete = new File(selectedFile);
					if(fileToDelete.delete()) {
						JOptionPane.showMessageDialog(this,"File deleted Successfully ");
						updateFileList();
						System.out.println("Delete is Executed Successfully: "+selectedFile);
					}
					else {
						JOptionPane.showMessageDialog(this,"Failed to delete the File. ");
						System.out.println("Failed to Delete: "+selectedFile);
					}
				}
				else {
					JOptionPane.showMessageDialog(this,"No file selected for deletion. ");
					System.out.println("No file Selected to Delete ");
				}
			}
		}
		
		if(e.getSource() == gotoDir) {
		  if(directory.getParentFile() != null) {
			  directory = directory.getParentFile();
			  updateFileList();
			  System.out.println("gotoDir is Working");
		  }
	}
	}
}

