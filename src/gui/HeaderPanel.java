package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class HeaderPanel extends JPanel {

	private JLabel lblTitle;
	private JLabel lblSubTitle;

	public HeaderPanel() {
		
		setBackground(new Color(0, 0, 0, 255));
		setLayout(new GridLayout(0,1));
		
		//set size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMaximumSize(new Dimension(screenSize.width, screenSize.height/4));
		setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		
		lblTitle=new JLabel("Top Quiz",SwingConstants.CENTER);
		lblTitle.setToolTipText("Top Quiz - Challenge Yourself");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBackground(new Color(103, 227, 86));
		lblTitle.setFont(new Font("Times New Roman", Font.ITALIC, 40));
		
		lblSubTitle=new JLabel("Test Your Knowledge!",SwingConstants.CENTER);
		lblSubTitle.setForeground(new Color(255, 255, 255));
		lblSubTitle.setBackground(new Color(103, 227, 86));
		lblSubTitle.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		
		//add to layout
		add(lblTitle);
		add(lblSubTitle);
		

	}

}
