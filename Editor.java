import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Editor extends JPanel implements ActionListener {
		File file;
		JButton save = new JButton("Save");
		JButton savec = new JButton("Save and Close");
		JButton back = new JButton("Back");
		JButton exit = new JButton("Exit");
		JTextArea text = new JTextArea(50,60);

		JScrollPane scrollPane = new JScrollPane(text);
		String[] fontSizes = {"12","14", "16","18","20","22","24","26", "28", "30"};
		JComboBox<String> fontSizeDropdown = new JComboBox<>(fontSizes);

		public Editor(String s) throws IOException {
		
			file = new File(s);
			save.addActionListener(this);
			savec.addActionListener(this);
			back.addActionListener(this);
			exit.addActionListener(this);
		    fontSizeDropdown.addActionListener(this);
		    
			if(file.exists()) {
				try {
					BufferedReader input = new BufferedReader(new FileReader(file));
					String line= input.readLine();
					while(line != null) {
						text.append(line+"\n");
						line=input.readLine();
					}
					input.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			JPanel panel = new JPanel(new GridLayout(1,5));
			panel.add(fontSizeDropdown);
			panel.add(save);
			panel.add(savec);
			panel.add(back);
			panel.add(exit);
			setLayout(new BorderLayout());
		
			add(panel, BorderLayout.NORTH);
			add(scrollPane, BorderLayout.CENTER);
			
			System.out.println("Editor is Executed Successfully");
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == fontSizeDropdown) {
			int size = Integer.parseInt((String) fontSizeDropdown.getSelectedItem());
            text.setFont(new Font(text.getFont().getName(), text.getFont().getStyle(), size));
            System.out.println("Font size changed to: " + size);
            
		}
		
		try {
			if(e.getSource() == savec || e.getSource() == save) {
				FileWriter out = new FileWriter(file);
				out.write(text.getText());
				out.close();
				if(e.getSource() == savec) {
					FileBrowser FBrowser = (FileBrowser) getParent();
					FBrowser.cl.show(FBrowser, "fileList");
			    System.out.println("Editor filewriter  and save and close  is Executed Successfully");
			  }
				System.out.println("Editor Save is Executed Successfully");
			}		
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(e.getSource() == back) {
		
			FileBrowser FBrowser = (FileBrowser) getParent();
			FBrowser.cl.show(FBrowser, "fileList");
			System.out.println("Back is Executed Succesfully");
		}
		
		if(e.getSource() == exit) {
			int option = JOptionPane.showConfirmDialog(this, "Do you want to save changes before exiting?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    FileWriter out = new FileWriter(file);
                    out.write(text.getText());
                    out.close();
                    FileBrowser FBrowser = (FileBrowser) getParent();
                    FBrowser.cl.show(FBrowser, "fileList");
                    System.out.println("YES OPTION WORKING");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (option == JOptionPane.NO_OPTION) {
                FileBrowser FBrowser = (FileBrowser) getParent();
                FBrowser.cl.show(FBrowser, "fileList");
                System.out.println("NO OPTION WORKING");
            } else if(option == JOptionPane.CANCEL_OPTION) {
            	
            		System.out.println("CANCEL WORKING");
            }
            System.out.println("Exit is Executed Successfully ");
		}  
	}
	
}
