package bmurki.finalproject.gui;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a summary of the user's quiz results,
 * including total questions, correct answers, and user statistics.
 */
public class ScoreSummary {

	private int totalQuestions;
	private int correctAnswers;
	private Map<String, Double> statistics = new HashMap<String, Double>();

	public final int SCORE_VALUE = 1;

	/**
	 * Retrieves the user's name from the currentUser.txt file.
	 * @return the user's name
	 */
	public String getUserName() {
		String name2 = null;
		try {
			FileInputStream in = new FileInputStream("currentUser.txt");
			ObjectInputStream obj2 = new ObjectInputStream(in);
			name2 = (String) obj2.readObject();
			obj2.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		}
		return name2;
	}

	/**
	 * Saves the user's score to a file named userScore.txt.
	 * @param scoresToSave a HashMap containing the scores to save
	 */
	public void saveUserScore(HashMap<String, Integer> scoresToSave) {
		String name = getUserName();
		int score = getTotalScore();
		try {
			File file = new File("userScore.txt");
			FileWriter myWriter = new FileWriter(file, true);
			myWriter.write(name + " " + scoresToSave.get("ENTERTAINMENT") + " " + scoresToSave.get("SCIENCE") + " " + scoresToSave.get("GEOGRAPHY") + "\n");
			myWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters and setters
	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Map<String, Double> getStatistics() {
		return statistics;
	}

	public void setStatistics(Map<String, Double> statistics) {
		this.statistics = statistics;
	}

	/**
	 * Calculates the total score based on correct answers and score value.
	 * @return the total score
	 */
	public int getTotalScore() {
		return correctAnswers * SCORE_VALUE;
	}
}
