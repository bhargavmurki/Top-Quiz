/**

 A class representing a ScienceQuiz, which is a specific type of QuestionBank.

 This class loads the questions from the "science.txt" file and maintains a list of unique questions

 that have not yet been used in the quiz. It provides a method to retrieve a random question from the list of unique questions.
 */

package bmurki.finalproject.data;

import java.util.ArrayList;
import java.util.Random;

public class ScienceQuiz extends QuestionBank {
    /**
     Constructs a new ScienceQuiz by calling the constructor of the superclass (QuestionBank) with the filename "science.txt".
     It initializes the list of unique questions with the list of questions obtained from the superclass.
     */

    public ScienceQuiz()
    {
        super("science.txt");
        uniqueQuestionList=getQuestionList();
    }
    private static ArrayList<Question> uniqueQuestionList=new ArrayList<Question>();
    /**
     Retrieves a random question from the list of unique questions.
     If the list of unique questions is empty, it returns null.
     It also removes the chosen question from the list of unique questions so that it is not repeated in the quiz.
     @return a randomly chosen question from the list of unique questions, or null if the list is empty.
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