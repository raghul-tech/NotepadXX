package com.notepadxx.notepadxx;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class SplashScreen {

    private JWindow splashScreen;
    private float opacity = 1.0f; // Fade-out effect
    private final int FADE_OUT_SPEED = 30; // Delay in ms for fade effect
  private    JProgressBar progressBar ;
    
    public SplashScreen() {
        FlatMacDarkLaf.setup(); // Apply FlatLaf theme

        splashScreen = new JWindow();
        splashScreen.setLayout(new BorderLayout());

        // Create the main panel
        JPanel panel = createStyledPanel();

        // Logo Panel with Centering
        JLabel logoLabel = createImageLabel("/icons/LogoX.png", 150, 150); // Increased Size

        // App Name & Version
       // JLabel appNameLabel = createLabel("NotepadXX", 22, Font.BOLD);
        //JLabel versionLabel = createLabel("v1.2.0", 14, Font.PLAIN);

        JLabel appNameLabel = new JLabel("NotepadXX");
        appNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        appNameLabel.setForeground(Color.WHITE);
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel versionLabel = new JLabel("v1.2.0");
        versionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        versionLabel.setForeground(new Color(180, 180, 180));
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Centering Container (Logo + Name + Version)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 5, 0);
        centerPanel.add(logoLabel, gbc);
        
        gbc.gridy = 1;
        centerPanel.add(appNameLabel, gbc);

        gbc.gridy = 2;
        centerPanel.add(versionLabel, gbc);

        // Progress Bar (Moved Up)
        progressBar = createStyledProgressBar();
        progressBar.setBorder(new EmptyBorder(10, 10, 20, 10)); // Added Padding to Move Up

        // Add components
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.SOUTH);

        splashScreen.getContentPane().add(panel);
        splashScreen.setSize(420, 330); // Reduced Height a Bit
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setAlwaysOnTop(true);
        splashScreen.setOpacity(opacity);
        splashScreen.setVisible(true);
        
       // animateProgressBar();
        
    }

    @SuppressWarnings("serial")
	private JPanel createStyledPanel() {
        return new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 30, 30, 230)); // Dark semi-transparent background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
    }

   /* private JLabel createLabel(String text, int size, int style) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("JetBrains Mono", style, size));
        label.setForeground(new Color(180, 180, 180)); // Soft gray
        return label;
    }*/

    private JProgressBar createStyledProgressBar() {
        JProgressBar progressBar = new JProgressBar();
      //  progressBar.setIndeterminate(true);
        progressBar.setForeground(new Color(0, 180, 255)); // Bright cyan color
        progressBar.setBackground(new Color(50, 50, 50));
        progressBar.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressBar.setStringPainted(false);
        return progressBar;
    }

    private JLabel createImageLabel(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }
    protected void animateProgressBar() {
        Timer timer = new Timer(15, e -> {
            int currentValue = progressBar.getValue();
            if (currentValue < 100) {
                progressBar.setValue(currentValue + 1);
            } else {
                ((Timer) e.getSource()).stop();
                fadeOutAndDispose(); // Start fade-out effect when progress reaches 100
            }
        });
        timer.start();
    }
 

    protected void fadeOutAndDispose() {
        Timer fadeTimer = new Timer(FADE_OUT_SPEED, e -> {
            opacity -= 0.05f;
            if (opacity <= 0) {
                splashScreen.dispose();
                ((Timer) e.getSource()).stop();
            } else {
                splashScreen.setOpacity(opacity);
            }
        });
        fadeTimer.start();
    }
    
    public void waitForLoadingToComplete(Runnable loadTask) {
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
            	Thread.sleep(50);
                loadTask.run(); // Run file-loading process in the background
           
                return null;
            }

            @Override
            protected void done() {
            //	loadTask.run(); 
                SwingUtilities.invokeLater(() -> fadeOutAndDispose()); // Only close splash screen when progress = 100%
            }
        };
        worker.execute();
    	//loadTask.run();
    }


    public void dispose() {
    	if(progressBar.getValue() >= 100) {
    		// progressBar.setForeground(new Color(0, 180, 0)); // Green when complete
    		   Timer timer = new Timer(100, e -> {
	                //synchronized (this) {
	            	
	                    if (splashScreen != null) {
	                        splashScreen.dispose();
	                         splashScreen = null;
	                    }
	                    progressBar = null;
	                   
	              //  }
	            	 
	            });
	            timer.setRepeats(false);
	            timer.start();
    	  
    	}
    }
    public int getvalue() {
    	return progressBar.getValue();
    }
    public  void setprogressValue(int n) {
    	SwingUtilities.invokeLater(() -> progressBar.setValue(n));
    //	progressBar.setValue(n);
    }
}
