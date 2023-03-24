/**

 The MainFrameUI class represents the main window of the quiz application.
 It extends the JFrame class and contains several panels for the layout and components.
 */
package bmurki.finalproject.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrameUI extends JFrame {

//member components

	private JLabel lblTitle;
	private JButton btnStart;
	private JLabel lblError;
	private HeaderUI headerPane;
	private SubjectUI subjectPane;
	private QuizUI quizPane;
	private Container contentPane;
	private String subjectChosen;

	/**
	 * Initializes all member variables to null or default values.
	 */
	private void initValues()
	{
		headerPane=null;
		subjectPane=null;
		quizPane=null;
		contentPane=null;
		subjectChosen=null;
	}

	/**
	 * Constructor for the MainFrameUI class. Creates the main window of the quiz application.
	 */
	public MainFrameUI() {

		super("TOP QUIZ");

		initValues();
		//get content pane
		contentPane=getContentPane();
		contentPane.setBackground(new Color(255, 255, 255));

		//FRAME properties
		//set default size and minimum size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setMinimumSize(new Dimension(300,300));
		//center the frame on screen
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		headerPane=new HeaderUI();

		//set layout and add components

		contentPane.setLayout(new BorderLayout());
		contentPane.add(headerPane,BorderLayout.NORTH);
		createWelcomePanel();


	}

	/**
	 * Creates the welcome panel that allows users to choose a topic for the quiz.
	 */
	private void createWelcomePanel()
	{
		JPanel welcomePane=new JPanel();
		welcomePane.setLayout(new GridLayout(0, 1));
		welcomePane.setPreferredSize(new Dimension(300,300));

		//topic selection panel
		subjectPane=new SubjectUI();
		subjectPane.setSubjectListener(new SubjectListener() {

			@Override
			public void subjectChosen(String subject) {
				// update the subject chosen for quiz
				subjectChosen=subject;

			}
		});

		lblError=new JLabel("",SwingConstants.CENTER);

		JPanel startPane=new JPanel();
		btnStart=new JButton();
		btnStart.setBorderPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setIcon(new ImageIcon("./Resources/LayoutImages/startbutton.png"));
		btnStart.setToolTipText("Start Playing");
		btnStart.setPreferredSize(new Dimension(100, 100));
		startPane.add(btnStart,Component.RIGHT_ALIGNMENT);
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Validate Subject selection
				if(subjectChosen!=null)
				{
					welcomePane.setVisible(false);
					//create quiz panel
					quizPane=new QuizUI(subjectChosen);
					quizPane.setVisible(true);
					contentPane.add(quizPane,BorderLayout.CENTER);//add quizPane to contentPane
				}
				else
				{
					//show error message
					lblError.setText("Select a topic");//add lblError to layout
					lblError.setForeground(Color.RED);
				}
			}
		});

		// Score Panel
		JPanel scorePane=new JPanel();
		JButton btnScore = new JButton();
		btnScore.setBorderPainted(false);
		btnScore.setContentAreaFilled(false);
		btnScore.setIcon(new ImageIcon("./Resources/LayoutImages/viewscores.png"));
		btnScore.setToolTipText("View Scores");
		btnScore.setPreferredSize(new Dimension(100, 100));
		scorePane.add(btnScore,Component.RIGHT_ALIGNMENT);
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Validating Subject selection
				welcomePane.setVisible(false);

				//create quiz panel
				ScoreUI scoreUI = new ScoreUI(getUserName());
				scoreUI.setVisible(true);
				//add quizPane to contentPane
				contentPane.add(scoreUI,BorderLayout.CENTER);
			}
		});

		// add components to welcomePane
		welcomePane.add(subjectPane);
		welcomePane.add(startPane,Component.RIGHT_ALIGNMENT);

		welcomePane.add(scorePane,Component.LEFT_ALIGNMENT);
		welcomePane.add(lblError);

		//add to content pane
		contentPane.add(welcomePane,BorderLayout.CENTER);

	}

	/**

	 This method retrieves the name of the current user from the "currentUser.txt" file.

	 @return A string containing the name of the current user.
	 */
	public String getUserName(){

		String name2 = null;
		try{
			// Open input stream to read from "currentUser.txt" file
			FileInputStream in = new FileInputStream("currentUser.txt");
			// Create object input stream to read from the input stream
			ObjectInputStream obj2 = new ObjectInputStream(in);

			// Read the name of the current user from the input stream and assign it to a string variable
			name2 = (String) obj2.readObject();

			// Print the name of the current user to the console for debugging purposes
			System.out.println("Name is: " + name2);

		}
		catch(FileNotFoundException e){
		// If the file is not found, print an error message to the console
			System.out.println("File not found");
		}
		catch(IOException e){
		// If an IO exception occurs, print an error message to the console
			System.out.println("IO Exception");
		}
		catch(ClassNotFoundException e){
		// If the class for object serialization is not found, print an error message to the console
			System.out.println("Class not found");
		}

		// Return the name of the current user
		return name2;
	}

}
