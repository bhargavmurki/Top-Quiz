/**
 A class representing a quiz for the Entertainment subject, extending the QuestionBank abstract class.
 */

package bmurki.finalproject.data;
import java.util.ArrayList;
import java.util.Random;

public class EntertainmentQuiz extends QuestionBank {

    /**
     * Constructor for the EntertainmentQuiz class, initializing the question list for the subject.
     */
    public EntertainmentQuiz()
    {
        super("entertainment.txt"); // call to the superclass constructor
        uniqueQuestionList=getQuestionList();
    }

    private static ArrayList<Question> uniqueQuestionList=new ArrayList<Question>();

    /**
     * Returns a random question from the list of unique questions for the Entertainment subject.
     * Removes the chosen question from the list to ensure that it is not chosen again.
     *
     * @return a randomly chosen Question object, or null if the list is empty
     */
    public Question getRandomQuestion() {
        if(uniqueQuestionList.size() == 0)
            return null;

        int randomIndex = new Random().nextInt(uniqueQuestionList.size());
        Question newQuestion = uniqueQuestionList.get(randomIndex);
        uniqueQuestionList.remove(randomIndex);

        return newQuestion;
    }

}

