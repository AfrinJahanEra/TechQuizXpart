package ProjectCode;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TechQuizXPartSwing {

    private static User currentUser;
    private static LeaderBoard leaderBoard = new LeaderBoard();
    private static Quiz currentQuiz;
    private static JFrame frame;
    private static JLabel photoLabel;

    public static void main(String[] args) {
        frame = new JFrame("TechQuizXPart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(700, 400);

        ImageIcon image = new ImageIcon(
                "C:\\Users\\afrin\\Downloads\\Messenger_creation_84f24402-dc57-4236-8bf3-8f6299665ff0.jpeg");
        if (image.getImageLoadStatus() == MediaTracker.COMPLETE) {
            frame.setIconImage(image.getImage());
        } else {
            System.err.println("Error loading image");
        }

        frame.getContentPane().setBackground(new Color(225, 246, 255));

        photoLabel = new JLabel(new ImageIcon("C:\\Users\\afrin\\Downloads\\photo.jpg"));
        photoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        showWelcomeScreen();
    }

    private static void showWelcomeScreen() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(225, 246, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(225, 246, 255));

        JLabel welcomeLabel = new JLabel("Welcome to TechQuizXPart", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        panel.add(welcomeLabel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int step = 0;
            String text = "Welcome to TechQuizXPart";

            @Override
            public void run() {
                if (step <= text.length()) {
                    welcomeLabel.setText(text.substring(0, step));
                    step++;
                } else {
                    timer.cancel();
                    showLoginScreen();
                }
            }
        }, 0, 100);
    }

    private static void showLoginScreen() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(225, 246, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Add gaps: 10 pixels horizontal and vertical
        panel.setBackground(new Color(225, 246, 255));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 25));

        JLabel universityLabel = new JLabel("University:");
        JTextField universityField = new JTextField();
        universityField.setPreferredSize(new Dimension(200, 25));

        JLabel deptLabel = new JLabel("Department:");
        JComboBox<String> deptComboBox = new JComboBox<>(new String[] { "CSE", "EEE", "CE", "ME" });

        JLabel subjectLabel = new JLabel("Subject:");
        JComboBox<Subject> subjectComboBox = new JComboBox<>();
        deptComboBox.addActionListener(e -> {
            subjectComboBox.removeAllItems();
            String selectedDept = (String) deptComboBox.getSelectedItem();
            switch (selectedDept) {
                case "CSE":
                    subjectComboBox.addItem(Subject.OOP);
                    subjectComboBox.addItem(Subject.DSA);
                    subjectComboBox.addItem(Subject.AI);
                    break;
                case "EEE":
                    subjectComboBox.addItem(Subject.ELECTRICAL_CIRCUIT);
                    subjectComboBox.addItem(Subject.POWER_SYSTEM);
                    subjectComboBox.addItem(Subject.DLD);
                    break;
                case "CE":
                    subjectComboBox.addItem(Subject.SOIL_MECHANICS);
                    subjectComboBox.addItem(Subject.SOLID_MECHANICS);
                    subjectComboBox.addItem(Subject.GEOLOGY);
                    break;
                case "ME":
                    subjectComboBox.addItem(Subject.FLUID_MECHANICS);
                    subjectComboBox.addItem(Subject.STRUCTURAL_DYNAMICS);
                    subjectComboBox.addItem(Subject.THERMODYNAMICS);
                    break;
            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(120, 40));

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String university = universityField.getText();
            String dept = (String) deptComboBox.getSelectedItem();
            Subject subject = (Subject) subjectComboBox.getSelectedItem();

            if (name.isEmpty() || university.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name and University cannot be empty.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!name.matches("[a-zA-Z\\s]+") || !university.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(frame,
                        "Invalid Name or University format. Only letters and spaces are allowed.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (dept == null || subject == null) {
                JOptionPane.showMessageDialog(frame, "Please select both Department and Subject.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            currentUser = new User(name, university, dept, subject);
            showOptionsScreen();
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(universityLabel);
        panel.add(universityField);
        panel.add(deptLabel);
        panel.add(deptComboBox);
        panel.add(subjectLabel);
        panel.add(subjectComboBox);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.setBackground(new Color(225, 246, 255));

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    private static void showOptionsScreen() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(225, 246, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(225, 246, 255));

        // for Adding label to the top
        JLabel titleLabel = new JLabel("Which One Do You Prefer-", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(225, 246, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        Dimension buttonSize = new Dimension(200, 50);

        JButton takeQuizButton = new JButton("Take Quiz");
        takeQuizButton.setPreferredSize(buttonSize);
        takeQuizButton.addActionListener(e -> startQuiz());
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(takeQuizButton, gbc);

        JButton addQuestionButton = new JButton("Add Question");
        addQuestionButton.setPreferredSize(buttonSize);
        addQuestionButton.addActionListener(e -> addQuestion());
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(addQuestionButton, gbc);

        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static void startQuiz() {
        currentQuiz = new Quiz(currentUser);
        showQuizQuestion();
    }

    private static void showQuizQuestion() {
        frame.getContentPane().removeAll();

        if (currentQuiz.hasMoreQuestions()) {
            Question question = currentQuiz.getNextQuestion();

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(new Color(225, 246, 255));

            // Question Panel
            JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextArea questionArea = new JTextArea();
            questionArea.setText(question.getQuestion());
            questionArea.setEditable(false);
            questionArea.setWrapStyleWord(true);
            questionArea.setLineWrap(true);
            questionArea.setPreferredSize(new Dimension(600, 60));
            questionArea.setFont(new Font(null, Font.BOLD, 15));
            questionPanel.add(questionArea);
            questionPanel.setBackground(new Color(225, 246, 255));

            ButtonGroup group = new ButtonGroup();
            JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
            optionsPanel.setBackground(new Color(225, 246, 255));

            for (String option : question.getOptions()) {
                JRadioButton optionButton = new JRadioButton(option);
                optionButton.setActionCommand(option);
                optionButton.setBackground(new Color(225, 246, 255));
                group.add(optionButton);
                optionsPanel.add(optionButton);
            }

            JProgressBar timerBar = new JProgressBar(0, 5);
            timerBar.setValue(5);

            JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            timerPanel.setBackground(new Color(225, 246, 255));
            timerPanel.add(timerBar);

            mainPanel.add(questionPanel, BorderLayout.NORTH);
            mainPanel.add(optionsPanel, BorderLayout.CENTER);
            mainPanel.add(timerPanel, BorderLayout.SOUTH);

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int count = 5;

                @Override
                public void run() {
                    count--;
                    timerBar.setValue(count);
                    if (count == 0) {
                        timer.cancel();
                        String selectedOption = group.getSelection() != null ? group.getSelection().getActionCommand()
                                : "";
                        currentQuiz.submitAnswer(selectedOption);
                        showQuizQuestion();
                    }
                }
            }, 1000, 1000);

            frame.add(mainPanel);
            frame.revalidate();
            frame.repaint();
        } else {
            showQuizResult();
        }
    }

    private static void showQuizResult() {
        double score = currentQuiz.getScore();
        JOptionPane.showMessageDialog(frame, "Your score is: " + score, "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
        leaderBoard.addScore(currentUser, score);

        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(225, 246, 255));

        JLabel label = new JLabel("Which Option Do You Prefer-");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(label);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goBackButton.setPreferredSize(new Dimension(200, 50));
        goBackButton.setMaximumSize(new Dimension(200, 50));
        goBackButton.addActionListener(e -> showLoginScreen());

        JButton finishQuizButton = new JButton("Finish Quiz");
        finishQuizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        finishQuizButton.setPreferredSize(new Dimension(200, 50));
        finishQuizButton.setMaximumSize(new Dimension(200, 50));
        finishQuizButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank You!", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardButton.setPreferredSize(new Dimension(200, 50));
        leaderboardButton.setMaximumSize(new Dimension(200, 50));
        leaderboardButton.addActionListener(e -> showLeaderboard(currentUser.getSubject()));

        panel.add(goBackButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(finishQuizButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(leaderboardButton);

        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static void showLeaderboard(Subject subject) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(225, 246, 255));

        List<Map.Entry<User, Double>> leaderboardEntries = leaderBoard.getLeaderboard(subject);
        String[] columnNames = { "Rank", "Name", "University", "Score" };
        String[][] data = new String[leaderboardEntries.size()][4];

        for (int i = 0; i < leaderboardEntries.size(); i++) {
            Map.Entry<User, Double> entry = leaderboardEntries.get(i);
            User user = entry.getKey();
            double score = entry.getValue();
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = user.getName();
            data[i][2] = user.getUniversity();
            data[i][3] = String.format("%.2f", score);
        }

        JTable leaderboardTable = new JTable(data, columnNames);
        leaderboardTable.setBackground(new Color(225, 246, 255));
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.getViewport().setBackground(new Color(225, 246, 255));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(225, 246, 255));

        JLabel titleLabel = new JLabel("Leaderboard for " + subject, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> showLoginScreen());

        JButton finishQuizButton = new JButton("Finish Quiz");
        finishQuizButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for participating!", "Thank You",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(goBackButton);
        buttonPanel.add(finishQuizButton);
        buttonPanel.setBackground(new Color(225, 246, 255));

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private static void addQuestion() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setBackground(new Color(225, 246, 255));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(225, 246, 255));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(new Color(225, 246, 255));

        JLabel questionLabel = new JLabel("Question:");
        JTextField questionField = new JTextField();
        questionField.setPreferredSize(new Dimension(200, 25));

        JLabel option1Label = new JLabel("Option 1:");
        JTextField option1Field = new JTextField();
        option1Field.setPreferredSize(new Dimension(200, 25));

        JLabel option2Label = new JLabel("Option 2:");
        JTextField option2Field = new JTextField();
        option2Field.setPreferredSize(new Dimension(200, 25));

        JLabel option3Label = new JLabel("Option 3:");
        JTextField option3Field = new JTextField();
        option3Field.setPreferredSize(new Dimension(200, 25));

        JLabel option4Label = new JLabel("Option 4:");
        JTextField option4Field = new JTextField();
        option4Field.setPreferredSize(new Dimension(200, 25));

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            try {
                String questionText = questionField.getText();
                String[] options = {
                        option1Field.getText(),
                        option2Field.getText(),
                        option3Field.getText(),
                        option4Field.getText()
                };
                if (options[0].isEmpty() || options[1].isEmpty() || options[2].isEmpty() || options[3].isEmpty()) {
                    throw new Exception("All options must be filled out.");
                }
                int correctIndex = Integer
                        .parseInt(JOptionPane.showInputDialog("Enter the index of the correct answer (1-4):")) - 1;
                if (correctIndex < 0 || correctIndex > 3) {
                    throw new Exception("Invalid index for the correct answer.");
                }
                Question newQuestion = new Question(questionText, options, options[correctIndex],
                        currentUser.getSubject());
                Quiz.addQuestion(newQuestion);
                JOptionPane.showMessageDialog(frame, "Question added successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                showOptionsScreen();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(e -> showLoginScreen());

        formPanel.add(questionLabel);
        formPanel.add(questionField);
        formPanel.add(option1Label);
        formPanel.add(option1Field);
        formPanel.add(option2Label);
        formPanel.add(option2Field);
        formPanel.add(option3Label);
        formPanel.add(option3Field);
        formPanel.add(option4Label);
        formPanel.add(option4Field);
        formPanel.add(new JLabel("")); // Placeholder for alignment
        formPanel.add(new JLabel(""));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.add(goBackButton);
        buttonPanel.setBackground(new Color(225, 246, 255));

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}
