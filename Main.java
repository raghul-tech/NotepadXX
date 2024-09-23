import javax.swing.JFrame;

public class Main {
	
	public static void main(String [] args) {
		
		JFrame frame = new JFrame("NotePadXX");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900,900);
		FileBrowser FBrowser = new FileBrowser();
		frame.add(FBrowser);
		frame.setVisible(true);
		
		System.out.println("Main is Executed Successfully ");		
	}
	
}