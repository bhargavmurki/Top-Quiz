package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class QuizPanel extends JPanel {

	
	private SubjectPanel subjectPane;
	private QuestionPanel questionPane;
	private SummaryPanel summaryPane;
	
	private String quizSubject;
	//setter
	public void setQuizSubject(String quizSubject) {
		this.quizSubject = quizSubject;
	}

	private void initValues()
	{
		subjectPane=null;
		questionPane=null;
		summaryPane=null;
	}

	public QuizPanel(String subject) {
		initValues();
		setQuizSubject(subject);
		setPreferredSize(new Dimension(550,500));

		//Live score update panel
		JPanel liveScorePane=new JPanel();
		JLabel lblScore = new JLabel("Score : 0",SwingConstants.LEFT);
		lblScore.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblScore.setForeground(new Color(211, 74, 49, 228));
		liveScorePane.add(lblScore,JComponent.LEFT_ALIGNMENT);

		//Subject panel
		subjectPane=new SubjectPanel(subject);

		//Question panel
		questionPane=new QuestionPanel(quizSubject);
		
		//for score update and topic selection
		JPanel rightPane=new JPanel();
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
		rightPane.add(subjectPane);

		//for questions and rightpane(score,topic)
		JPanel lowerPane=new JPanel();
		lowerPane.setLayout(new BoxLayout(lowerPane, BoxLayout.X_AXIS));
		lowerPane.add(questionPane);
		lowerPane.add(rightPane);
		//layout
		add(lowerPane);
		
		
		//listeners
		subjectPane.setSubjectListener(new SubjectListener() {
			
			public void subjectChosen(String subject) {
				// update the subject chosen for quiz, and the quiz subject
				setQuizSubject(subject);
				questionPane.setQuizSubject(subject);
				
			}
		});

		questionPane.setScoreListener(new ScoreListener() {
			
			public void scoreUpdated(int score) {
				// update new score to label
				lblScore.setText("Score : "+score);
				
			}
		});

		questionPane.setSummaryListener(new SummaryListener() {
			
			public void quizEnded(ScoreSummary summary) {
				// pass score summary to summary pane and display summary+graph
				
				//Summary panel
				summaryPane=new SummaryPanel(summary);
				add(summaryPane);
				
				//hide questionPane
				lowerPane.setVisible(false);
			}
		});
	}
}
