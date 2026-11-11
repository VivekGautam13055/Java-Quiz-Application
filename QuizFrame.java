import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QuizFrame extends JFrame {
    private final Quiz quiz;
    private final JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel progressLabel = new JLabel("", SwingConstants.CENTER);
    private final JRadioButton[] optionButtons = new JRadioButton[4];
    private final ButtonGroup optionGroup = new ButtonGroup();
    private final JButton nextButton = new JButton("Next");
    private final JPanel contentPanel = new JPanel(new BorderLayout(15, 15));

    public QuizFrame() {
        quiz = new Quiz();
        setTitle("Java Knowledge Quiz");
        setSize(650, 430);
        setMinimumSize(new Dimension(520, 360));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        contentPanel.setBackground(Color.WHITE);
        setContentPane(contentPanel);

        JLabel heading = new JLabel("Java Knowledge Quiz", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        contentPanel.add(heading, BorderLayout.NORTH);

        JPanel questionPanel = new JPanel();
        questionPanel.setOpaque(false);
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(Box.createVerticalStrut(25));
        questionPanel.add(questionLabel);
        questionPanel.add(Box.createVerticalStrut(25));

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionButtons[i].setOpaque(false);
            optionGroup.add(optionButtons[i]);
            questionPanel.add(optionButtons[i]);
            questionPanel.add(Box.createVerticalStrut(12));
        }
        contentPanel.add(questionPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomPanel.add(progressLabel, BorderLayout.WEST);
        nextButton.addActionListener(event -> moveToNextQuestion());
        bottomPanel.add(nextButton, BorderLayout.EAST);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        showQuestion();
    }

    private void showQuestion() {
        Question question = quiz.getCurrentQuestion();
        questionLabel.setText("<html><div style='text-align:center;width:480px'>"
                + quiz.getCurrentQuestionNumber() + ". "
                + question.getQuestionText() + "</div></html>");
        progressLabel.setText("Question " + quiz.getCurrentQuestionNumber()
                + " of " + quiz.getTotalQuestions());

        String[] options = question.getOptions();
        optionGroup.clearSelection();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(options[i]);
        }
        nextButton.setText(quiz.getCurrentQuestionNumber() == quiz.getTotalQuestions()
                ? "Finish" : "Next");
    }

    private void moveToNextQuestion() {
        int selectedIndex = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select an answer before continuing.",
                    "Answer required", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        quiz.submitAnswer(selectedIndex);
        if (quiz.isFinished()) {
            showResult();
        } else {
            showQuestion();
        }
    }

    private void showResult() {
        String message = "Quiz completed!\n\nYour score: " + quiz.getScore()
                + " / " + quiz.getTotalQuestions()
                + "\nPercentage: " + String.format("%.1f", quiz.getPercentage()) + "%"
                + "\n\nWould you like to try again?";
        int choice = JOptionPane.showConfirmDialog(this, message,
                "Quiz Result", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            quiz.restart();
            showQuestion();
        } else {
            dispose();
        }
    }
}
