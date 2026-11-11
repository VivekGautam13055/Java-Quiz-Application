public class Question {
    private final String questionText;
    private final String[] options;
    private final int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        if (questionText == null || questionText.isBlank()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        if (options == null || options.length < 2) {
            throw new IllegalArgumentException("A question must have at least two options.");
        }
        if (correctAnswerIndex < 0 || correctAnswerIndex >= options.length) {
            throw new IllegalArgumentException("Correct answer index is out of range.");
        }

        this.questionText = questionText;
        this.options = options.clone();
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options.clone();
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
