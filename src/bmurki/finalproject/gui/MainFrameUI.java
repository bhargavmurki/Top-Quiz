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
	private HeaderPanel headerPane;
	private SubjectPanel subjectPane;
	private QuizPanel quizPane;
	private Container contentPane;
	private String subjectChosen;
	private void initValues()
	{
		headerPane=null;
		subjectPane=null;
		quizPane=null;
		contentPane=null;
		subjectChosen=null;
		
	}

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
		
		headerPane=new HeaderPanel();
		
		//set layout and add components
		
		contentPane.setLayout(new BorderLayout());
		contentPane.add(headerPane,BorderLayout.NORTH);
		createWelcomePanel();
		
		
	}

	private void createWelcomePanel()
	{
		JPanel welcomePane=new JPanel();
		welcomePane.setLayout(new GridLayout(0, 1));
		welcomePane.setPreferredSize(new Dimension(300,300));
		
		//topic selection panel
		subjectPane=new SubjectPanel();
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
					quizPane=new QuizPanel(subjectChosen);
					quizPane.setVisible(true);
					contentPane.add(quizPane,BorderLayout.CENTER);//add quizPane to contentPane
				}
				else
				{
					//show error message
					lblError.setText("Please select a topic");//add lblError to layout
					lblError.setForeground(Color.RED);
				}
			}
		});


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
				// Validate Subject selection
				welcomePane.setVisible(false);

				//create quiz panel
				ScorePanel scorePanel = new ScorePanel(getUserName());
				scorePanel.setVisible(true);
				contentPane.add(scorePanel,BorderLayout.CENTER);//add quizPane to contentPane
			}
		});

		welcomePane.add(subjectPane);
		welcomePane.add(startPane,Component.RIGHT_ALIGNMENT);

		welcomePane.add(scorePane,Component.LEFT_ALIGNMENT);
		welcomePane.add(lblError);
		
		contentPane.add(welcomePane,BorderLayout.CENTER);//add to content pane
		
	}

	public String getUserName(){

		String name2 = null;
		try{
			FileInputStream in = new FileInputStream("currentUser.txt");
			ObjectInputStream obj2 = new ObjectInputStream(in);
			name2 = (String) obj2.readObject();
			System.out.println("Name is: " + name2);
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(IOException e){
			System.out.println("IO Exception");
		}
		catch(ClassNotFoundException e){
			System.out.println("Class not found");
		}
		return name2;
	}

}
