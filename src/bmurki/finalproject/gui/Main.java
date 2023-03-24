/**
 The Main class contains the main method which runs the application.
 It sets the cross-platform look and feel for the GUI and creates an instance of the UserPageUI.
 */
package bmurki.finalproject.gui;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
				} catch (Exception e) {
					e.printStackTrace();
				}
				new UserPageUI();

			}
		});
	}
}
