/**

 The HeaderUI class is a JPanel that displays the title and subtitle of the quiz.
 It is designed to be displayed at the top of the quiz's main window.
 */
package bmurki.finalproject.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HeaderUI extends JPanel {

	//member controls

	private JLabel lblTitle;
	private JLabel lblSubTitle;

	/**
	 * Constructs a new HeaderUI object and sets its layout and background color.
	 * Also sets the maximum size and alignment of the panel.
	 */
	public HeaderUI() {

		setBackground(new Color(0, 0, 0, 255));
		setLayout(new GridLayout(0,1));

		//set size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMaximumSize(new Dimension(screenSize.width, screenSize.height/4));
		setAlignmentX(JComponent.CENTER_ALIGNMENT);

		//create title label
		lblTitle = new JLabel("Top Quiz",SwingConstants.CENTER);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBackground(new Color(103, 227, 86));
		lblTitle.setFont(new Font("Times New Roman", Font.ITALIC, 40));

		//create subtitle label
		lblSubTitle=new JLabel("Test Your Knowledge!",SwingConstants.CENTER);
		lblSubTitle.setForeground(new Color(255, 255, 255));
		lblSubTitle.setBackground(new Color(103, 227, 86));
		lblSubTitle.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		//add labels to layout
		add(lblTitle);
		add(lblSubTitle);
	}

}
