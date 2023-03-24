package bmurki.finalproject.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class represents the main user interface for the quiz application.
 * It includes a panel for displaying the quiz subject, questions, and summary.
 */
public class QuizUI extends JPanel {

	// UI components for displaying subject, questions, and summary
	private SubjectUI subjectPane;
	private QuestionUI questionPane;
	private SummaryUI summaryPane;

	// Variable to store the quiz subject
	private String quizSubject;

	/**
	 * Setter for the quiz subject.
	 * @param quizSubject the subject of the quiz
	 */
	public void setQuizSubject(String quizSubject) {
		this.quizSubject = quizSubject;
	}

	/**
	 * Initializes the member variables to null.
	 */
	private void initValues() {
		subjectPane = null;
		questionPane = null;
		summaryPane = null;
	}

	/**
	 * Constructor for the QuizUI class.
	 * @param subject the subject of the quiz
	 */
	public QuizUI(String subject) {
		initValues();
		setQuizSubject(subject);
		setPreferredSize(new Dimension(550, 500));

		// Live score update panel
		JPanel liveScorePane = new JPanel();
		JLabel lblScore = new JLabel("Score : 0", SwingConstants.LEFT);
		lblScore.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblScore.setForeground(new Color(211, 74, 49, 228));
		liveScorePane.add(lblScore, JComponent.LEFT_ALIGNMENT);

		// Subject panel
		subjectPane = new SubjectUI(subject);

		// Question panel
		questionPane = new QuestionUI(quizSubject);

		// For score update and topic selection
		JPanel rightPane = new JPanel();
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
		rightPane.add(subjectPane);

		// For questions and rightPane (score, topic)
		JPanel lowerPane = new JPanel();
		lowerPane.setLayout(new BoxLayout(lowerPane, BoxLayout.X_AXIS));
		lowerPane.add(questionPane);
		lowerPane.add(rightPane);

		// Add the lowerPane to the main layout
		add(lowerPane);

		// Add listeners
		// Subject listener
		subjectPane.setSubjectListener(new SubjectListener() {
			public void subjectChosen(String subject) {
				// Update the subject chosen for quiz, and the quiz subject
				setQuizSubject(subject);
				questionPane.setQuizSubject(subject);
			}
		});

		// Score listener
		questionPane.setScoreListener(new ScoreListener() {
			public void scoreUpdated(int score) {
				// Update new score to label
				lblScore.setText("Score : " + score);
			}
		});

		// Summary listener
		questionPane.setSummaryListener(new SummaryListener() {
			public void quizEnded(ScoreSummary summary) {
				// Pass score summary to summary pane and display summary + graph

				// Summary panel
				summaryPane = new SummaryUI(summary);
				add(summaryPane);

				// Hide questionPane
				lowerPane.setVisible(false);
			}
		});
	}
}
