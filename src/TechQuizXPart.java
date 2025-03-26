package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.List;

import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TechQuizXPart {
    private static User currentUser;
    private static LeaderBoard leaderBoard = new LeaderBoard();
    private static QuestionRepository questionRepo = new QuestionRepository();
    private static Quiz currentQuiz;
    private static JFrame frame;

    // Color Scheme
    private static final Color PRIMARY_COLOR = new Color(63, 81, 181);
    private static final Color SECONDARY_COLOR = new Color(255, 255, 255);
    private static final Color ACCENT_COLOR = new Color(255, 152, 0);
    private static final Color BACKGROUND_COLOR = new Color(250, 250, 250);
    private static final Color TEXT_COLOR = new Color(33, 33, 33);
    private static final Color CARD_COLOR = new Color(255, 255, 255);

    // Fonts
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font QUESTION_FONT = new Font("Segoe UI", Font.PLAIN, 18);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("TechQuizXPart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLocationRelativeTo(null);

        // Set application icon
        try {
            frame.setIconImage(new ImageIcon("icon.png").getImage());
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
        }

        showWelcomeScreen();
        frame.setVisible(true);
    }

    private static void showWelcomeScreen() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        // Background Panel with Gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, getWidth(), getHeight(),
                        new Color(81, 45, 168));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // App Logo/Title
        JLabel titleLabel = new JLabel("TechQuizXPart");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(SECONDARY_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Test Your Knowledge. Challenge Yourself.");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(255, 255, 255, 180));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Start Button
        JButton startButton = createStyledButton("GET STARTED", ACCENT_COLOR, 200, 50);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> showLoginScreen());

        // Add components with spacing
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(subtitleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        contentPanel.add(startButton);
        contentPanel.add(Box.createVerticalGlue());

        backgroundPanel.add(contentPanel);
        frame.add(backgroundPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        // Fade-in animation
        fadeInComponents(contentPanel);
    }

    private static void showLoginScreen() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(40, 80, 40, 80));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Create Your Profile");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Enter your details to get started");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(0, 0, 0, 120));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Name Field
        JTextField nameField = createFormTextField("Full Name");

        // University Field
        JTextField universityField = createFormTextField("University");

        // Department Dropdown
        String[] departments = { "CSE", "EEE", "CE", "ME" };
        JComboBox<String> deptComboBox = createStyledComboBox(departments);

        // Subject Dropdown (dynamically populated)
        JComboBox<Subject> subjectComboBox = createStyledComboBox(new Subject[] {});
        deptComboBox.addActionListener(e -> {
            String selectedDept = (String) deptComboBox.getSelectedItem();
            subjectComboBox.removeAllItems();
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

        // Add form fields with labels
        addFormField(formPanel, "Full Name", nameField);
        addFormField(formPanel, "University", universityField);
        addFormField(formPanel, "Department", deptComboBox);
        addFormField(formPanel, "Subject", subjectComboBox);

        // Submit Button
        JButton submitButton = createStyledButton("CONTINUE", PRIMARY_COLOR, 200, 50);
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String university = universityField.getText().trim();
            String dept = (String) deptComboBox.getSelectedItem();
            Subject subject = (Subject) subjectComboBox.getSelectedItem();

            if (name.isEmpty() || university.isEmpty()) {
                showErrorDialog("Name and University cannot be empty.");
                return;
            }

            if (!name.matches("[a-zA-Z\\s]+") || !university.matches("[a-zA-Z\\s]+")) {
                showErrorDialog("Invalid Name or University format. Only letters and spaces are allowed.");
                return;
            }

            if (dept == null || subject == null) {
                showErrorDialog("Please select both Department and Subject.");
                return;
            }

            currentUser = new User(name, university, dept, subject);
            showOptionsScreen();
        });

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(submitButton);

        // Add panels to content
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void showOptionsScreen() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(40, 80, 40, 80));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("What would you like to do?");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Choose an option to continue");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(0, 0, 0, 120));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Options Cards Panel
        JPanel cardsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        cardsPanel.setOpaque(false);

        // Take Quiz Card
        JPanel quizCard = createOptionCard(
                "Take Quiz",
                "Test your knowledge with a timed quiz",
                "quiz_icon.png",
                PRIMARY_COLOR);
        quizCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startQuiz();
            }
        });

        // Add Question Card
        JPanel addQuestionCard = createOptionCard(
                "Add Question",
                "Contribute to our question bank",
                "add_icon.png",
                ACCENT_COLOR);
        addQuestionCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addQuestion();
            }
        });

        cardsPanel.add(quizCard);
        cardsPanel.add(addQuestionCard);

        // Add panels to content
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(cardsPanel, BorderLayout.CENTER);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void startQuiz() {
        List<Question> questions = questionRepo.getRandomQuestionsByDepartmentAndSubject(
                currentUser.getDepartment(),
                currentUser.getSubject(),
                5);

        if (questions.isEmpty()) {
            showErrorDialog("No questions available for this subject yet.");
            return;
        }

        currentQuiz = new Quiz(currentUser, questions);
        showQuizQuestion();
    }

    private static void showQuizQuestion() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        if (currentQuiz.hasMoreQuestions()) {
            Question question = currentQuiz.getNextQuestion();

            // Main Content Panel
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBackground(BACKGROUND_COLOR);
            contentPanel.setBorder(new EmptyBorder(30, 50, 30, 50));

            // Header with progress
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setOpaque(false);

            JLabel progressLabel = new JLabel(
                    String.format("Question %d of %d",
                            currentQuiz.getCurrentQuestionIndex(),
                            currentQuiz.getTotalQuestions()),
                    SwingConstants.LEFT);
            progressLabel.setFont(SUBTITLE_FONT);
            progressLabel.setForeground(new Color(0, 0, 0, 150));

            JProgressBar progressBar = new JProgressBar(0, currentQuiz.getTotalQuestions());
            progressBar.setValue(currentQuiz.getCurrentQuestionIndex());
            progressBar.setForeground(PRIMARY_COLOR);
            progressBar.setBackground(new Color(230, 230, 230));
            progressBar.setBorderPainted(false);
            progressBar.setStringPainted(false);
            progressBar.setPreferredSize(new Dimension(0, 6));

            headerPanel.add(progressLabel, BorderLayout.NORTH);
            headerPanel.add(progressBar, BorderLayout.CENTER);
            headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

            // Question Panel
            JPanel questionPanel = new JPanel(new BorderLayout());
            questionPanel.setOpaque(false);
            questionPanel.setBorder(new EmptyBorder(20, 0, 30, 0));

            JTextArea questionArea = new JTextArea(question.getQuestion());
            questionArea.setEditable(false);
            questionArea.setWrapStyleWord(true);
            questionArea.setLineWrap(true);
            questionArea.setFont(QUESTION_FONT);
            questionArea.setForeground(TEXT_COLOR);
            questionArea.setOpaque(false);
            questionArea.setBorder(new EmptyBorder(10, 10, 10, 10));

            questionPanel.add(questionArea, BorderLayout.CENTER);

            // Options Panel
            JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
            optionsPanel.setOpaque(false);
            optionsPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

            ButtonGroup group = new ButtonGroup();
            for (String option : question.getOptions()) {
                JRadioButton optionButton = createOptionButton(option);
                group.add(optionButton);
                optionsPanel.add(optionButton);
            }

            // Timer Panel
            JPanel timerPanel = new JPanel(new BorderLayout());
            timerPanel.setOpaque(false);

            JProgressBar timerBar = new JProgressBar(0, 10);
            timerBar.setValue(10);
            timerBar.setForeground(ACCENT_COLOR);
            timerBar.setBackground(new Color(230, 230, 230));
            timerBar.setBorderPainted(false);
            timerBar.setStringPainted(false);
            timerBar.setPreferredSize(new Dimension(0, 6));

            timerPanel.add(timerBar, BorderLayout.CENTER);

            // Add panels to content
            contentPanel.add(headerPanel, BorderLayout.NORTH);
            contentPanel.add(questionPanel, BorderLayout.CENTER);
            contentPanel.add(optionsPanel, BorderLayout.CENTER);
            contentPanel.add(timerPanel, BorderLayout.SOUTH);

            frame.add(contentPanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();

            // Timer logic
            Timer timer = new Timer(1000, e -> {
                int count = timerBar.getValue() - 1;
                timerBar.setValue(count);
                if (count == 0) {
                    ((Timer) e.getSource()).stop();
                    String selectedOption = group.getSelection() != null ? group.getSelection().getActionCommand() : "";
                    currentQuiz.submitAnswer(selectedOption);
                    showQuizQuestion();
                }
            });
            timer.start();
        } else {
            showQuizResult();
        }
    }

    private static void showQuizResult() {
        double score = currentQuiz.getScore();
        leaderBoard.addScore(currentUser, score);

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(40, 80, 40, 80));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Quiz Completed!");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel(String.format("Your Score: %.1f", score));
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        scoreLabel.setForeground(PRIMARY_COLOR);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        headerPanel.add(scoreLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        JButton leaderboardButton = createStyledButton("VIEW LEADERBOARD", PRIMARY_COLOR, 250, 50);
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardButton.addActionListener(e -> showLeaderboard(currentUser.getSubject()));

        JButton restartButton = createStyledButton("TAKE ANOTHER QUIZ", ACCENT_COLOR, 250, 50);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(e -> showOptionsScreen());

        JButton exitButton = createStyledButton("EXIT", new Color(200, 200, 200), 250, 50);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));

        buttonsPanel.add(leaderboardButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonsPanel.add(restartButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonsPanel.add(exitButton);

        // Add panels to content
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(buttonsPanel, BorderLayout.CENTER);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void showLeaderboard(Subject subject) {
        List<Map.Entry<User, Double>> leaderboardEntries = leaderBoard.getLeaderboard(subject);

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(40, 80, 40, 80));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(subject.toString());
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(PRIMARY_COLOR);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Leaderboard Table
        String[] columnNames = { "Rank", "Name", "University", "Score" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        for (int i = 0; i < leaderboardEntries.size(); i++) {
            Map.Entry<User, Double> entry = leaderboardEntries.get(i);
            User user = entry.getKey();
            double score = entry.getValue();

            model.addRow(new Object[] {
                    i + 1,
                    user.getName(),
                    user.getUniversity(),
                    String.format("%.1f", score)
            });
        }

        JTable leaderboardTable = new JTable(model);
        leaderboardTable.setFont(SUBTITLE_FONT);
        leaderboardTable.setRowHeight(40);
        leaderboardTable.setShowGrid(false);
        leaderboardTable.setIntercellSpacing(new Dimension(0, 0));
        leaderboardTable.getTableHeader().setFont(BUTTON_FONT);
        leaderboardTable.setFillsViewportHeight(true);

        // Customize table appearance
        leaderboardTable.getTableHeader().setBackground(PRIMARY_COLOR);
        leaderboardTable.getTableHeader().setForeground(Color.WHITE);
        leaderboardTable.setSelectionBackground(PRIMARY_COLOR.brighter());
        leaderboardTable.setSelectionForeground(Color.WHITE);

        // Center align all columns except names
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        for (int i = 0; i < leaderboardTable.getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setCellRenderer(
                    i == 1 ? leftRenderer : centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        // Back Button
        JButton backButton = createStyledButton("BACK TO MENU", PRIMARY_COLOR, 200, 50);
        backButton.addActionListener(e -> showOptionsScreen());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);

        // Add panels to content
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private static void addQuestion() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(40, 80, 40, 80));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Add New Question");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Contribute to our question bank");
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(0, 0, 0, 120));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Question Field
        JTextArea questionArea = new JTextArea();
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(QUESTION_FONT);
        questionArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 30)),
                BorderFactory.createEmptyBorder(8, 0, 8, 0)));
        JScrollPane questionScroll = new JScrollPane(questionArea);
        questionScroll.setBorder(BorderFactory.createTitledBorder("Question Text"));

        // Options Fields
        JTextField[] optionFields = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            optionFields[i] = createFormTextField("Option " + (i + 1));
        }

        // Add form fields
        formPanel.add(questionScroll);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        for (JTextField field : optionFields) {
            formPanel.add(field);
            formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        // Submit Button
        JButton submitButton = createStyledButton("SUBMIT QUESTION", PRIMARY_COLOR, 200, 50);
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String questionText = questionArea.getText().trim();
            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                options[i] = optionFields[i].getText().trim();
            }

            if (questionText.isEmpty()) {
                showErrorDialog("Question text cannot be empty.");
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (options[i].isEmpty()) {
                    showErrorDialog("Option " + (i + 1) + " cannot be empty.");
                    return;
                }
            }

            try {
                int correctIndex = Integer.parseInt(
                        JOptionPane.showInputDialog("Enter the number of the correct answer (1-4):")) - 1;

                if (correctIndex < 0 || correctIndex > 3) {
                    showErrorDialog("Please enter a number between 1 and 4.");
                    return;
                }

                Question newQuestion = new Question(
                        questionText,
                        options,
                        options[correctIndex],
                        currentUser.getSubject());

                questionRepo.addQuestion(currentUser.getDepartment(), newQuestion);
                JOptionPane.showMessageDialog(frame,
                        "Question added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                showOptionsScreen();
            } catch (NumberFormatException ex) {
                showErrorDialog("Please enter a valid number for the correct answer.");
            }
        });

        // Back Button
        JButton backButton = createStyledButton("CANCEL", new Color(200, 200, 200), 200, 50);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.addActionListener(e -> showOptionsScreen());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        formPanel.add(buttonPanel);

        // Add panels to content
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.CENTER);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // ===== UI HELPER METHODS =====

    private static JButton createStyledButton(String text, Color bgColor, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(bgColor);
        button.setForeground(SECONDARY_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setPreferredSize(new Dimension(width, height));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private static JTextField createFormTextField(String placeholder) {
        JTextField textField = new JTextField();
        textField.setFont(SUBTITLE_FONT);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 30)),
                BorderFactory.createEmptyBorder(8, 0, 8, 0)));
        textField.setOpaque(false);
        textField.setPreferredSize(new Dimension(300, 40));
        textField.putClientProperty("JTextField.placeholderText", placeholder);
        return textField;
    }

    private static <T> JComboBox<T> createStyledComboBox(T[] items) {
        JComboBox<T> comboBox = new JComboBox<>(items);
        comboBox.setFont(SUBTITLE_FONT);
        comboBox.setBackground(SECONDARY_COLOR);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 30)),
                BorderFactory.createEmptyBorder(8, 0, 8, 0)));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                return this;
            }
        });
        return comboBox;
    }

    private static void addFormField(JPanel panel, String labelText, Component field) {
        JLabel label = new JLabel(labelText);
        label.setFont(SUBTITLE_FONT);
        label.setForeground(new Color(0, 0, 0, 150));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        ((JComponent) field).setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private static JPanel createOptionCard(String title, String description, String iconPath, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 0, 10)),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 1, 1, color),
                        BorderFactory.createEmptyBorder(30, 30, 30, 30)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 0, 10)),
                        BorderFactory.createEmptyBorder(30, 30, 30, 30)));
            }
        });

        // Icon
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                JLabel iconLabel = new JLabel(new ImageIcon(
                        icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                iconLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
                card.add(iconLabel, BorderLayout.NORTH);
            }
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
        }

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(TEXT_COLOR);

        // Description
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(SUBTITLE_FONT);
        descLabel.setForeground(new Color(0, 0, 0, 120));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(descLabel);

        card.add(textPanel, BorderLayout.CENTER);
        return card;
    }

    private static JRadioButton createOptionButton(String text) {
        JRadioButton button = new JRadioButton(text);
        button.setActionCommand(text);
        button.setFont(SUBTITLE_FONT);
        button.setForeground(TEXT_COLOR);
        button.setOpaque(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 0, 20)),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 1, 1, PRIMARY_COLOR),
                        BorderFactory.createEmptyBorder(15, 20, 15, 20)));
            }

            public void mouseExited(MouseEvent e) {
                if (!button.isSelected()) {
                    button.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 0, 20)),
                            BorderFactory.createEmptyBorder(15, 20, 15, 20)));
                }
            }
        });

        button.addActionListener(e -> {
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, PRIMARY_COLOR),
                    BorderFactory.createEmptyBorder(15, 20, 15, 20)));
        });

        return button;
    }

    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private static void fadeInComponents(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JComponent) {
                ((JComponent) comp).setOpaque(false);
                comp.setForeground(new Color(
                        comp.getForeground().getRed(),
                        comp.getForeground().getGreen(),
                        comp.getForeground().getBlue(),
                        0));
            }
        }

        Timer fadeTimer = new Timer(30, new ActionListener() {
            float opacity = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.05f;
                if (opacity >= 1) {
                    opacity = 1;
                    ((Timer) e.getSource()).stop();
                }

                for (Component comp : panel.getComponents()) {
                    if (comp instanceof JComponent) {
                        comp.setForeground(new Color(
                                comp.getForeground().getRed(),
                                comp.getForeground().getGreen(),
                                comp.getForeground().getBlue(),
                                (int) (opacity * 255))); // Added missing parenthesis here
                    }
                }
                panel.repaint();
            }
        });
        fadeTimer.start();
    }

}