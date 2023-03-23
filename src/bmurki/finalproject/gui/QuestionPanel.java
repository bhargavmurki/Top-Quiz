package bmurki.finalproject.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import bmurki.finalproject.data.AllSubjects;
import bmurki.finalproject.data.EntertainmentQuiz;
import bmurki.finalproject.data.GeographyQuiz;
import bmurki.finalproject.data.Question;
import bmurki.finalproject.data.QuestionBank;
import bmurki.finalproject.data.QuestionType;
import bmurki.finalproject.data.ScienceQuiz;


public class QuestionPanel extends JPanel {

	private JLabel lblNum;
	private JTextArea lblQuestion;
	private JButton btnSubmit;
	private JButton btnNext;
	private JButton btnEnd;
	private JRadioButton rdoSelected;
	//private JLabel lblError;
	private JPanel innerQPane;
	private JPanel buttonPane;

	
	private JLabel lblQuestionImage;


	private ActionListener radioBtnEventHandler;
	private ScoreListener scoreListener;
	private SummaryListener summaryListener;
	private static int score=0; //total score
	private static int attempted=0; //total questions attempted
	private static int correctAnswers=0; //no. of questions answered correctly
	private boolean isSubmitted=false;
	
	private final String LAYOUTIMAGEPATH = "./Resources/LayoutImages/";
	
	private Question newQuestion;
	protected QuestionBank qb;
	
	//to get questions for each topic
	private static GeographyQuiz geographyQuiz =new GeographyQuiz();
	private static ScienceQuiz scienceQuiz =new ScienceQuiz();
	private static EntertainmentQuiz entertainmentQuiz =new EntertainmentQuiz();
	
	private String quizSubject;

	private void initValues()
	{
		geographyQuiz = null;
		scienceQuiz = null;
		entertainmentQuiz = null;
		newQuestion = null;
		qb = null;
		attempted = 0;
		score = 0;
		correctAnswers = 0;
		isSubmitted = false;
	}

	public QuestionPanel(String quizSubject) {

		initValues();
		setPreferredSize(new Dimension(800, 700));
		setQuizSubject(quizSubject);
		quizStart();

		setAlignmentY(JComponent.CENTER_ALIGNMENT);


		//add and populate controls with the question and options
		innerQPane=new JPanel();
		createQuestionAndOptions();
		add(innerQPane, JComponent.CENTER_ALIGNMENT);
		createButtons();

	}
	
	//setters
	public void setScoreListener(ScoreListener scoreListener) {
		this.scoreListener = scoreListener;
	}
	public void setSummaryListener(SummaryListener summaryListener) {
		this.summaryListener = summaryListener;
	}
	public void setQuizSubject(String quizSubject) {
		this.quizSubject = quizSubject;
	}
	
	
	//methods
	private void createQuestionAndOptions()
	{
		//get a random question from question bank
		newQuestion=qb.getRandomQuestion();
		if(newQuestion==null)
		{
			isSubmitted=false;
			JOptionPane.showMessageDialog(null,
					"You have attempted all the questions in this topic.",
					"Top Quiz - Information",
				    JOptionPane.INFORMATION_MESSAGE);
			
			endQuiz();
			
		}
		else {
		
		innerQPane.setLayout(new BoxLayout(innerQPane, BoxLayout.Y_AXIS));
		innerQPane.setSize(750, 400);
		
		JPanel numPane=new JPanel();
		lblNum=new JLabel("<html>Question "+(attempted)+" of 10<br/><br/></html>");
		lblNum.setFont(new Font("Times New Roman", Font.BOLD, 20));
		numPane.add(lblNum,JComponent.LEFT_ALIGNMENT);
		
		JPanel qTextPane=new JPanel();
		qTextPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuestion=new JTextArea();
		lblQuestion.setLineWrap(true);
		lblQuestion.setWrapStyleWord(true);
		lblQuestion.setFont(new Font("Times new roman", Font.BOLD, 15));
		lblQuestion.setText(newQuestion.getQuestionText());//question
		lblQuestion.setEditable(false);
		lblQuestion.setOpaque(false);
		lblQuestion.setSize(750, 100);
		qTextPane.add(lblQuestion);
		
		
		innerQPane.add(numPane);
		innerQPane.add(qTextPane);
		
		if(newQuestion.getQuestionType() == QuestionType.IMAGEQUESTION)
		{
			//set image for question
			JPanel qImgPane=new JPanel();
			qImgPane.setAlignmentX(Component.CENTER_ALIGNMENT);
			ImageIcon qIcon=new ImageIcon(newQuestion.getQuestionImage());
			lblQuestionImage=new JLabel(qIcon,SwingConstants.CENTER);
			lblQuestionImage.setPreferredSize(new Dimension(250, 250));
			
			qImgPane.add(lblQuestionImage);
			innerQPane.add(qImgPane,Component.CENTER_ALIGNMENT);
			
		}
		
		//options
		ButtonGroup optionGroup=new ButtonGroup();
		radioBtnEventHandler = new RadioButtonEventHandler();
		JPanel optionPane=new JPanel();
		optionPane.setLayout(new BoxLayout(optionPane, BoxLayout.Y_AXIS));
		
		optionPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		for(String option :newQuestion.getOptions())
		{
			JRadioButton newOption=new JRadioButton(option);
			newOption.setActionCommand(option);//to check answer on submission
			newOption.addActionListener(radioBtnEventHandler);
			optionGroup.add(newOption);//add to button group
			newOption.setOpaque(true);
			
			optionPane.add(newOption);
			
		}
		innerQPane.add(optionPane,Component.LEFT_ALIGNMENT);//add to layout
		}
	}

	private void createButtons()
	{
		buttonPane=new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		
		//button to submit answer
		ImageIcon submitIcon=new ImageIcon(LAYOUTIMAGEPATH+"SubmitBlueButton.png");
		btnSubmit=new JButton("Submit");
		
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setOpaque(true);
		btnSubmit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSubmit.setBackground(new Color(46, 162, 0));
		
		btnSubmit.setToolTipText("Submit");

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSubmitted=true;
				//Check answer
				if(rdoSelected!=null)
				{
					btnSubmit.setVisible(false);
					btnNext.setVisible(true);
					
					
					if(rdoSelected.getActionCommand().equals(newQuestion.getAnswer()))
					{
						rdoSelected.setSize(rdoSelected.getParent().getWidth(),rdoSelected.getHeight());
						score+=5;
						correctAnswers++;
						qb.incrementCorrectAnswers();
						
						scoreListener.scoreUpdated(score);
					}
					else
					{
						rdoSelected.setSize(rdoSelected.getParent().getWidth(),rdoSelected.getHeight());
					}
					
					//if 10 questions attempted, end quiz.
					if(attempted==10)
					{
						JOptionPane.showMessageDialog(null, "Quiz Completed, Click Next to See the Scores","Quiz completed",JOptionPane.INFORMATION_MESSAGE);
						endQuiz();
					}
				}
				else
				{
					isSubmitted=false;
					JOptionPane.showMessageDialog(null, "Please select an answer to continue",
							"Top Quiz - Alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnNext=new JButton("Next");
		btnNext.setForeground(new Color(255, 255, 255));
		btnNext.setOpaque(true);
		btnNext.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNext.setBackground(new Color(86, 165, 210));
		
		btnNext.setVisible(false);

		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isSubmitted=false;
				
				if(attempted == 10)
				{
					JOptionPane.showMessageDialog(null, "Quiz Completed, Click Next to See the Scores","Quiz completed",JOptionPane.INFORMATION_MESSAGE);
					endQuiz();
				}
				else {
					
					attempted++;
					setQuestionBank();
				innerQPane.removeAll();
				rdoSelected=null;
				createQuestionAndOptions();
				
				//show submit button
				btnSubmit.setVisible(true);
				//hide next button
				btnNext.setVisible(false);
				
				
	             Thread worker = new Thread() {
	            	  
	                  public void run()
	                  {
	                	  SwingUtilities.invokeLater( new Runnable(){
	                		  public void run()
	                		  {
//	                			  startAnimation();
	                		  }
	                	  });
	                  }
	             };
	             worker.start();
				}
			}
		});
		
		
		buttonPane.add(btnSubmit);
		buttonPane.add(btnNext);
		
		
		JPanel endButtonPane=new JPanel();
		endButtonPane.setLayout(new BoxLayout(endButtonPane,BoxLayout.X_AXIS));
		
		btnEnd=new JButton("End Quiz");
		
		btnEnd.setForeground(new Color(255, 255, 255));
		btnEnd.setOpaque(true);
		btnEnd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnEnd.setBackground(new Color(220, 41, 41));

		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isSubmitted)
					endQuiz();
				else
				{
					JOptionPane.showMessageDialog(null, "Select an Answer brfore quiting the quiz",
							"Top Quiz - Alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		endButtonPane.add(btnEnd);
		
		add(buttonPane,JComponent.CENTER_ALIGNMENT);
		add(endButtonPane,JComponent.RIGHT_ALIGNMENT);
	}

	class RadioButtonEventHandler implements ActionListener
	   {  
		//Saves the button that was selected.
	      public void actionPerformed(ActionEvent event){
	    	  rdoSelected=(JRadioButton)event.getSource();//to check answer on submit
	      }
	   }

	private void setQuestionBank()
	{
		switch (quizSubject) {
		case "Geography":
			qb = geographyQuiz;
			geographyQuiz.incrementQuestionsAttempted();
			break;

		case "Science":
			qb = scienceQuiz;
			scienceQuiz.incrementQuestionsAttempted();
			break;

		case "Entertainment":
			qb = entertainmentQuiz;
			entertainmentQuiz.incrementQuestionsAttempted();
			break;

		default:
			break;
		}
	}

	private void quizStart()
	{
		if (attempted == 0)//first time loading quiz
		{
			attempted++;//increment counter for first time load
			geographyQuiz =new GeographyQuiz();
			scienceQuiz =new ScienceQuiz();
			entertainmentQuiz =new EntertainmentQuiz();

			setQuestionBank();
		}
	}

    private void endQuiz(){
    	//calculate total percentage score of each subject
		HashMap<String, Integer> scoresToSave = new HashMap<String, Integer>();
    	Map<String,Double> percentScore = new HashMap<String,Double>();
    	if(geographyQuiz.getQuestionsAttempted()>0)
    	{
    		percentScore.put(AllSubjects.GEOGRAPHY.toString(), geographyQuiz.getPercentageScore());
			scoresToSave.put(AllSubjects.GEOGRAPHY.toString(), geographyQuiz.getCorrectAnswers());

    	}
    	if(scienceQuiz.getQuestionsAttempted()>0)
    	{
    		percentScore.put(AllSubjects.SCIENCE.toString(), scienceQuiz.getPercentageScore());
			scoresToSave.put(AllSubjects.SCIENCE.toString(), scienceQuiz.getCorrectAnswers());
    	}
    	if(entertainmentQuiz.getQuestionsAttempted()>0)
    	{
    		percentScore.put(AllSubjects.ENTERTAINMENT.toString(), entertainmentQuiz.getPercentageScore());
			scoresToSave.put(AllSubjects.ENTERTAINMENT.toString(), entertainmentQuiz.getCorrectAnswers());
    	}

    	ScoreSummary scoreSummary=new ScoreSummary();
    	scoreSummary.setTotalQuestions(attempted);
    	scoreSummary.setCorrectAnswers(correctAnswers);
    	scoreSummary.setStatistics(percentScore);
		scoreSummary.getUserName();
		scoreSummary.saveUserScore(scoresToSave);
    	summaryListener.quizEnded(scoreSummary);
    }
}