import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        loadQuestions();
        restart();
    }

    private void loadQuestions() {
        questions.add(new Question(
                "Which keyword is used to create a class in Java?",
                new String[]{"class", "define", "struct", "new"}, 0));
        questions.add(new Question(
                "Which data type stores true or false?",
                new String[]{"int", "boolean", "char", "String"}, 1));
        questions.add(new Question(
                "Which method is the entry point of a Java program?",
                new String[]{"start()", "run()", "main()", "init()"}, 2));
        questions.add(new Question(
                "Which collection stores elements in a resizable list?",
                new String[]{"ArrayList", "Scanner", "Math", "System"}, 0));
        questions.add(new Question(
                "Which keyword is used to inherit a class?",
                new String[]{"implements", "extends", "inherits", "super"}, 1));
    }

    public Question getCurrentQuestion() {
        if (isFinished()) {
            return null;
        }
        return questions.get(currentQuestionIndex);
    }

    // Records the selected option and moves to the next question.
    public boolean submitAnswer(int selectedOptionIndex) {
        if (isFinished()) {
            return false;
        }

        boolean correct = selectedOptionIndex ==
                questions.get(currentQuestionIndex).getCorrectAnswerIndex();
        if (correct) {
            score++;
        }
        currentQuestionIndex++;
        return correct;
    }

    public boolean isFinished() {
        return currentQuestionIndex >= questions.size();
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getScore() {
        return score;
    }

    public double getPercentage() {
        if (questions.isEmpty()) {
            return 0;
        }
        return score * 100.0 / questions.size();
    }

    public void restart() {
        currentQuestionIndex = 0;
        score = 0;
    }
}
