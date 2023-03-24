/**

 A class representing a single question object in the quiz system.
 */
package bmurki.finalproject.data;
import java.util.List;

public class Question {
    /**
     * Constructor for the Question class.
     */
    public Question() {}

    private int questionID;
    private String questionText;
    private List<String> options;
    private String answer;
    private QuestionType questionType;
    private String questionImage;

    /**
     * Getter for the question ID.
     *
     * @return an integer representing the unique ID of the question
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * Setter for the question ID.
     *
     * @param questionID an integer representing the unique ID of the question
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * Getter for the question text.
     *
     * @return a String representing the text of the question
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Setter for the question text.
     *
     * @param questionText a String representing the text of the question
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Getter for the list of options for the question.
     *
     * @return a List of Strings representing the options for the question
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Setter for the list of options for the question.
     *
     * @param options a List of Strings representing the options for the question
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * Getter for the correct answer for the question.
     *
     * @return a String representing the correct answer for the question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter for the correct answer for the question.
     *
     * @param answer a String representing the correct answer for the question
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Getter for the type of the question.
     *
     * @return a QuestionType object representing the type of the question
     */
    public QuestionType getQuestionType() {
        return questionType;
    }

    /**
     * Setter for the type of the question.
     *
     * @param questionType a QuestionType object representing the type of the question
     */
    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    /**
     * Getter for the image associated with the question.
     *
     * @return a String representing the image filename for the question, or null if there is no associated image
     */
    public String getQuestionImage() {
        return questionImage;
    }

    /**
     * Setter for the image associated with the question.
     *
     * @param questionImage a String representing the image filename for the question, or null if there is no associated image
     */
    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    /**
     * Overrides the toString method to provide a more readable representation of the question object.
     *
     * @return a String representing the question ID, question text, options, and correct answer for the question
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(questionID + ". " + questionText + "\n");
        sb.append("Options: " + options + "\n");
        sb.append("Answer: " + answer);

        return sb.toString();
    }
}
