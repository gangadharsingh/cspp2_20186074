/**
 * Quiz code.
 * @author: gangadharsingh.
 */
import java.util.*;
/**
 * Class for question.
 */
class Question {
    /**
     * { initializing a variable }.
     */
    private String questiontxt;
    /**
     * { initializing a variable }.
     */
    private String[] choices;
    /**
     * { index of correct answer }.
     */
    private int correctansrind;
    /**
     * { initializing a variable }.
     */
    private int maxmark;
    /**
     * { initializing a variable }.
     */
    private int penalty;
    /**
     * { initializing a variable }.
     */
    private String response;
    /**
     * Constructs the object.
     */
    Question() {

    }
    /**
     * Constructs the object.
     *
     * @param      question1       The question 1
     * @param      choices1        The choices 1
     * @param      correctansrind1  The correct answer 1
     * @param      maxmark1       The maximum marks 1
     * @param      penalty1        The penalty 1
     */
    Question(final String question1, final String[] choices1,
             final int correctansrind1, final int maxmark1, final int penalty1) {
        this.questiontxt = question1;
        this.choices = choices1;
        this.correctansrind = correctansrind1;
        this.maxmark = maxmark1;
        this.penalty = penalty1;
        this.response = "";

    }
    /**
     * { function_description }.
     *
     * @param      choice  The choice
     *
     * @return     { description_of_the_return_value }
     */
    public boolean evaluateResponse(final String choice) {
        return getCorrectAnswer().equals(choice);
    }
    /**
     * Gets the correct answer.
     *
     * @return     The correct answer.
     */
    public String getCorrectAnswer() {
        return choices[correctansrind - 1];
    }
    /**
     * Gets the question text.
     *
     * @return     The question text.
     */
    public String getquestiontxt() {
        return questiontxt;
    }
    /**
     * Gets the choice.
     *
     * @return     The choice.
     */
    public String[] getChoice() {
        return choices;
    }
    /**
     * Gets the maximum marks.
     *
     * @return     The maximum marks.
     */
    public int getMaxmark() {
        return maxmark;
    }
    /**
     * Gets the penalty.
     *
     * @return     The penalty.
     */
    public int getPenalty() {
        return penalty;
    }
    /**
     * Sets the response.
     *
     * @param      answer  The answer
     */
    public void setResponse(final String answer) {
        this.response = answer;
    }
    /**
     * Gets the response.
     *
     * @return     The response.
     */
    public String getResponse() {
        return this.response;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String s = "";
        s += questiontxt + '(' + maxmark + ')' + '\n';
        for (String choice : choices) {
            s += choice + '\t';
        }
        s = s.trim() + '\n';
        return s;
    }
}
/**
 * Class for quiz.
 */
class Quiz {
    /**
     * { initializing a variable }.
     */
    private final int onehundred = 100;
    /**
     * { initializing a variable }.
     */
    private Question[] questions;
    /**
     * { initializing a variable }.
     */
    private int size;
    /**
     * Constructs the object.
     */
    Quiz() {
        questions = new Question[10];
        size = 0;
    }
    /**
     * Adds a question.
     *
     * @param      q     The question
     */
    public void addQuestion(final Question q) {
        questions[size++] = q;
    }
    /**
     * Gets the question.
     *
     * @param      index  The index
     *
     * @return     The question.
     */
    public Question getQuestion(final int index) {
        return questions[index];
    }

    public Question[] getQuestions() {
        // System.out.println(Arrays.toString(Arrays.copyOf(questions, size)));
        return Arrays.copyOf(questions, size);
    }
    /**
     * Shows the report.
     *
     * @return     { description_of_the_return_value }
     */
    public String showReport() {
        String s = "";
        int marks = 0;

        for (Question question : getQuestions()) {
            s += question.getquestiontxt() + '\n' + ' ';
            if (question.evaluateResponse(question.getResponse())) {
                s += "Correct Answer! " + '-' + " Marks Awarded: "
                + question.getMaxmark();
                marks += question.getMaxmark();
            } else {
                s += "Wrong Answer! " + '-' + " Penalty: "
                + question.getPenalty();
                marks += question.getPenalty();
            }
            s += '\n';
        }
        s += "Total Score: " + marks;
        return s;
    }

}
/**
 * Solution class for code-eval.
 */
public final class Solution {
    /**
    * Constructs the object.
    */
    private Solution() {
        // leave this blank
    }
    /**
     * main function to execute test cases.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // instantiate this Quiz
        Quiz q = new Quiz();
        // code to read the test cases input file
        Scanner s = new Scanner(System.in);
        // check if there is one more line to process
        boolean flag = true;
        while (s.hasNext()) {
            // read the line
            String line = s.nextLine();
            // split the line using space
            String[] tokens = line.split(" ");

            // based on the list operation invoke the corresponding method
            switch (tokens[0]) {
            case "LOAD_QUESTIONS":
                System.out.println("|----------------|");
                System.out.println("| Load Questions |");
                System.out.println("|----------------|");
                try {
                    loadQuestions(s, q, Integer.parseInt(tokens[1]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    flag = false;
                }

                break;
            case "START_QUIZ":
                System.out.println("|------------|");
                System.out.println("| Start Quiz |");
                System.out.println("|------------|");
                startQuiz(s, q, Integer.parseInt(tokens[1]));
                break;
            case "SCORE_REPORT":
                System.out.println("|--------------|");
                System.out.println("| Score Report |");
                System.out.println("|--------------|");
                if (flag) {
                    displayScore(q);
                }
                break;
            default:
                break;
            }
        }
    }
    /**
     * Loads questions.
     *
     * @param      scan       The scan
     * @param      quiz       The quiz
     * @param      q          The question count
     *
     */
    public static void loadQuestions(final Scanner scan,
                                     final Quiz quiz, final int q) throws Exception {
        // write your code here to read the questions from the console
        // tokenize the question line and create the question object
        // add the question objects to the quiz class
        if (q == 0) throw new Exception("Quiz does not have questions");
        for (int  i = 0; i < q; i++) {
            String[] tokens = scan.nextLine().split(":");
            for (String token : tokens)
                if (token.equals("")) {
                    throw new Exception("Error! Malformed question");
                }
            if (tokens.length < 5) {
                throw new Exception("Error! Malformed question");
            }
            if (tokens[1].split(",").length < 2) {
                throw new Exception(tokens[0] + " does not have enough answer choices");
            }
            if (Integer.parseInt(tokens[2]) > tokens[1].split(",").length) {
                throw new Exception(
                    "Error! Correct answer choice number is out of range for question text "
                    + (i + 1));
            }
            if (Integer.parseInt(tokens[3]) < 0) {
                throw new Exception("Invalid max marks for " + tokens[0]);
            }
            if (Integer.parseInt(tokens[4]) > 0) {
                throw new Exception("Invalid penalty for " + tokens[0]);
            }
            quiz.addQuestion(new Question(tokens[0], tokens[1].split(
                ","), Integer.parseInt(tokens[2]), Integer.parseInt(
                tokens[3]), Integer.parseInt(tokens[4])));
            // System.out.println(i);
        }
        System.out.println(q + " are added to the quiz");


    }
    /**
     * Starts a quiz.
     *
     * @param      scan  The scan
     * @param      quiz  The quiz
     * @param      q     The answer count
     */
    public static void startQuiz(final Scanner scan,
                                 final Quiz quiz, final int q) {
        // write your code here to display the quiz questions on the console.
        // read the user responses from the console using scanner object.
        // store the user respone in the question object
        for (Question question : quiz.getQuestions()) {
            System.out.println(question);
            question.setResponse(scan.nextLine());
        }
    }
    /**
     * Displays the score report.
     *
     * @param      quiz     The quiz object
     */
    public static void displayScore(final Quiz quiz) {
        // write your code here to display the score report using quiz object.
        System.out.println(quiz.showReport());
    }
}