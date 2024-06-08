package ProjectCode;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;



public class TechQuizXpartConsole {

    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static LeaderBoard leaderBoard = new LeaderBoard();
    private static Quiz currentQuiz;

    public static void main(String[] args) {
        showWelcomeScreen();
    }

    private static void showWelcomeScreen() {
        System.out.println("Welcome to TechQuizXpart");
        showLoginScreen();
    }

    private static void showLoginScreen() {
        String name = getName();
        String university = getUniversity();
        String departmentChoice = getDepartmentChoice();

        List<Subject> subjects = getSubjects(departmentChoice);
        String department = getDepartmentName(departmentChoice);

        int subjectChoice = getSubjectChoice(subjects);

        Subject selectedSubject = subjects.get(subjectChoice - 1);

        currentUser = new User(name, university, department, selectedSubject);
        System.out.println("Name: " + currentUser.getName());
        System.out.println("University: " + currentUser.getUniversity());
        System.out.println("Department: " + currentUser.getDepartment());
        System.out.println("Subject: " + currentUser.getSubject());
        showOptionsScreen();
    }

    private static String getName() {
        while (true) {
            System.out.println("Enter your name:");
            String name = scanner.nextLine().trim();
            if (name.matches("[a-zA-Z\\s]+")) {
                return name;
            } else {
                System.out.println("Invalid Name format. Only letters and spaces are allowed.");
            }
        }
    }

    private static String getUniversity() {
        while (true) {
            System.out.println("Enter your university:");
            String university = scanner.nextLine().trim();//trim() is for removing white space from both sides of the string
            if (university.matches("[a-zA-Z\\s]+")) {
                return university;
            } else {
                System.out.println("Invalid University format. Only letters and spaces are allowed.");
            }
        }
    }

    private static String getDepartmentChoice() {
        while (true) {
            System.out.println("Choose your department: \n1) CSE \n2) EEE\n3) CE\n4) ME");
            String departmentChoice = scanner.nextLine().trim();
            if (departmentChoice.matches("[1-4]")) {
                return departmentChoice;
            } else {
                System.out.println("Invalid department selected. Please choose a valid option.");
            }
        }
    }

    private static List<Subject> getSubjects(String departmentChoice) {
        Map<String, List<Subject>> departmentSubjects = new HashMap<>();
        departmentSubjects.put("1", List.of(Subject.OOP, Subject.DSA, Subject.AI));
        departmentSubjects.put("2", List.of(Subject.ELECTRICAL_CIRCUIT, Subject.POWER_SYSTEM, Subject.DLD));
        departmentSubjects.put("3", List.of(Subject.SOIL_MECHANICS, Subject.SOLID_MECHANICS, Subject.GEOLOGY));
        departmentSubjects.put("4", List.of(Subject.FLUID_MECHANICS, Subject.STRUCTURAL_DYNAMICS, Subject.THERMODYNAMICS));
        return departmentSubjects.get(departmentChoice);
    }//We use HashMap as it is making it very efficient for looking up values based on a key. Here key are the departments. according to the key get the base index of required department and then search the data index (Subject list) of that BI If data index is valid then return 


    private static String getDepartmentName(String departmentChoice) {
        Map<String, String> departmentNames = new HashMap<>();
        departmentNames.put("1", "CSE");
        departmentNames.put("2", "EEE");
        departmentNames.put("3", "CE");
        departmentNames.put("4", "ME");
        return departmentNames.get(departmentChoice);
    }

    private static int getSubjectChoice(List<Subject> subjects) {
        while (true) {
            System.out.println("Choose your subject:");
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println((i + 1) + ". " + subjects.get(i));//show the subject of choosen department
            }

            try {
                int subjectChoice = Integer.parseInt(scanner.nextLine().trim());
                if (subjectChoice >= 1 && subjectChoice <= subjects.size()) {
                    return subjectChoice;
                } else {
                    System.out.println("Invalid subject selected. Please choose a valid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    
    private static void showOptionsScreen() {
        System.out.println("Which One Do You Prefer-");
        System.out.println("1. Take Quiz");
        System.out.println("2. Add Question");
        System.out.println("Enter the number of your choice:");

        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                startQuiz();
                break;
            case 2:
                addQuestion();
                break;
            default:
                System.out.println("Invalid choice. Please select either 1 or 2.");
                showOptionsScreen();
                break;
        }
    }


    private static void startQuiz() {
        currentQuiz = new Quiz(currentUser);
        showQuizQuestion();
    }



    private static void showQuizQuestion() {
        if (currentQuiz.hasMoreQuestions()) {
            Question question = currentQuiz.getNextQuestion();
            System.out.println(question.getQuestion());

            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            Scanner scanner = new Scanner(System.in);
            try {
                int userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= options.length) {

                    currentQuiz.submitAnswer(options[userAnswer - 1]);
                    showQuizQuestion();
                } else {
                    System.out.println("Invalid option. Please try again.");
                    showQuizQuestion();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                showQuizQuestion();
            }
        } else {
            showQuizResult();
        }
    }


    private static void showQuizResult() {
        double score = currentQuiz.getScore();
        System.out.println("Your score is: " + score);
        leaderBoard.addScore(currentUser, score);

        System.out.println("Which Option Do You Prefer-");
        System.out.println("1. Go Back");
        System.out.println("2. Finish Quiz");
        System.out.println("3. Leaderboard");
        System.out.println("Enter the number of your choice:");

        int choice = scanner.nextInt();
        scanner.nextLine();  

        switch (choice) {
            case 1:
                showLoginScreen();
                break;
            case 2:
                System.out.println("Thank You!");
                System.exit(0);
                break;
            case 3:
                showLeaderboard(currentUser.getSubject());
                break;
            default:
                System.out.println("Invalid choice. Please select either 1, 2, or 3.");
                showQuizResult();
                break;
        }
    }
        


    private static void showLeaderboard(Subject subject) {
        List<Map.Entry<User, Double>> leaderboardEntries = leaderBoard.getLeaderboard(subject);

        System.out.println("Leaderboard for " + subject + "\n");

        System.out.printf("%-5s %-20s %-30s %-10s\n", "Rank", "Name", "University", "Score");// prints the column headers ("Rank", "Name", "University", "Score") with specified widths
        int rank = 1;
        for (Map.Entry<User, Double> entry : leaderboardEntries) {
            User user = entry.getKey();
            double score = entry.getValue();
            System.out.printf("%-5d %-20s %-30s %-10.2f\n", rank++, user.getName(), user.getUniversity(), score);
        }

        System.out.println("\n1. Go Back");
        System.out.println("2. Finish Quiz");
        System.out.println("Enter the number of your choice:");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                showLoginScreen();
                break;
            case 2:
                System.out.println("Thank You!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please select either 1 or 2.");
                showLeaderboard(subject);
                break;
        }
    }
        

    private static void addQuestion() {
        System.out.println("Add a New Question\n");

        System.out.print("Enter the question: ");
        String questionText = scanner.nextLine();

        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.printf("Enter option %d: ", i + 1);
            options[i] = scanner.nextLine();
            if (options[i].isEmpty()) {
                System.out.println("All options must be filled out. Try again.");
                return;
            }
        }

        int correctIndex = -1;
        while (correctIndex < 1 || correctIndex > 4) {
            try {
                System.out.print("Enter the index of the correct answer (1-4): ");
                correctIndex = Integer.parseInt(scanner.nextLine());
                if (correctIndex < 1 || correctIndex > 4) {
                    System.out.println("Invalid index for the correct answer. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }

        try {
            Question newQuestion = new Question(questionText, options, options[correctIndex - 1], currentUser.getSubject());
            Quiz.addQuestion(newQuestion);
            System.out.println("Question added successfully!");

            System.out.println("\n1. Go Back");
            System.out.println("2. Take Quiz");
            System.out.print("Enter the number of your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    showOptionsScreen();
                    break;
                case 2:
                    startQuiz();
                    break;
                default:
                    System.out.println("Invalid choice. Please select either 1 or 2.");
                    showOptionsScreen();
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}



// public class TechQuizXpartConsole {

//     private static Scanner scanner = new Scanner(System.in);
//     private static User currentUser;
//     private static LeaderBoard leaderBoard = new LeaderBoard();
//     private static Quiz currentQuiz;

//     public static void main(String[] args) {
//         showWelcomeScreen();
//     }

//     private static void showWelcomeScreen() {
//         System.out.println("Welcome to TechQuizXpart");
//         showLoginScreen();
//     }

//     private static void showLoginScreen() {
//         String name = getName();
//         String university = getUniversity();
//         String departmentChoice = getDepartmentChoice();

//         List<Subject> subjects = getSubjects(departmentChoice);
//         String department = getDepartmentName(departmentChoice);

//         int subjectChoice = getSubjectChoice(subjects);

//         Subject selectedSubject = subjects.get(subjectChoice - 1);

//         currentUser = new User(name, university, department, selectedSubject);
//         System.out.println("Name: " + currentUser.getName());
//         System.out.println("University: " + currentUser.getUniversity());
//         System.out.println("Department: " + currentUser.getDepartment());
//         System.out.println("Subject: " + currentUser.getSubject());
//         showOptionsScreen();
//     }

//     private static String getName() {
//         while (true) {
//             System.out.println("Enter your name:");
//             String name = scanner.nextLine().trim();
//             if (name.matches("[a-zA-Z\\s]+")) {
//                 return name;
//             } else {
//                 System.out.println("Invalid Name format. Only letters and spaces are allowed.");
//             }
//         }
//     }

//     private static String getUniversity() {
//         while (true) {
//             System.out.println("Enter your university:");
//             String university = scanner.nextLine().trim();
//             if (university.matches("[a-zA-Z\\s]+")) {
//                 return university;
//             } else {
//                 System.out.println("Invalid University format. Only letters and spaces are allowed.");
//             }
//         }
//     }

//     private static String getDepartmentChoice() {
//         while (true) {
//             System.out.println("Choose your department: \n1) CSE \n2) EEE\n3) CE\n4) ME");
//             String departmentChoice = scanner.nextLine().trim();
//             if (departmentChoice.matches("[1-4]")) {
//                 return departmentChoice;
//             } else {
//                 System.out.println("Invalid department selected. Please choose a valid option.");
//             }
//         }
//     }

//     private static List<Subject> getSubjects(String departmentChoice) {
//         String[][] departmentSubjects = {
//             {"OOP", "DSA", "AI"},
//             {"ELECTRICAL_CIRCUIT", "POWER_SYSTEM", "DLD"},
//             {"SOIL_MECHANICS", "SOLID_MECHANICS", "GEOLOGY"},
//             {"FLUID_MECHANICS", "STRUCTURAL_DYNAMICS", "THERMODYNAMICS"}
//         };

//         int index = Integer.parseInt(departmentChoice) - 1;
//         return Arrays.stream(departmentSubjects[index]).map(Subject::fromString).toList();
//     }

//     private static String getDepartmentName(String departmentChoice) {
//         String[] departmentNames = {"CSE", "EEE", "CE", "ME"};
//         return departmentNames[Integer.parseInt(departmentChoice) - 1];
//     }

//     private static int getSubjectChoice(List<Subject> subjects) {
//         while (true) {
//             System.out.println("Choose your subject:");
//             for (int i = 0; i < subjects.size(); i++) {
//                 System.out.println((i + 1) + ". " + subjects.get(i));
//             }

//             try {
//                 int subjectChoice = Integer.parseInt(scanner.nextLine().trim());
//                 if (subjectChoice >= 1 && subjectChoice <= subjects.size()) {
//                     return subjectChoice;
//                 } else {
//                     System.out.println("Invalid subject selected. Please choose a valid option.");
//                 }
//             } catch (NumberFormatException e) {
//                 System.out.println("Invalid input. Please enter a number.");
//             }
//         }
//     }

//     private static void showOptionsScreen() {
//         System.out.println("Which One Do You Prefer-");
//         System.out.println("1. Take Quiz");
//         System.out.println("2. Add Question");
//         System.out.println("Enter the number of your choice:");

//         int choice = scanner.nextInt();
//         scanner.nextLine();  

//         switch (choice) {
//             case 1:
//                 startQuiz();
//                 break;
//             case 2:
//                 addQuestion();
//                 break;
//             default:
//                 System.out.println("Invalid choice. Please select either 1 or 2.");
//                 showOptionsScreen();
//                 break;
//         }
//     }

//     private static void startQuiz() {
//         currentQuiz = new Quiz(currentUser);
//         showQuizQuestion();
//     }

//     private static void showQuizQuestion() {
//         if (currentQuiz.hasMoreQuestions()) {
//             Question question = currentQuiz.getNextQuestion();
//             System.out.println(question.getQuestion());

//             String[] options = question.getOptions();
//             for (int i = 0; i < options.length; i++) {
//                 System.out.println((i + 1) + ". " + options[i]);
//             }

//             Scanner scanner = new Scanner(System.in);
//             try {
//                 int userAnswer = scanner.nextInt();
//                 if (userAnswer >= 1 && userAnswer <= options.length) {
//                     currentQuiz.submitAnswer(options[userAnswer - 1]);
//                     showQuizQuestion();
//                 } else {
//                     System.out.println("Invalid option. Please try again.");
//                     showQuizQuestion();
//                 }
//             } catch (Exception e) {
//                 System.out.println("Invalid input. Please enter a number.");
//                 showQuizQuestion();
//             }
//         } else {
//             showQuizResult();
//         }
//     }

//     private static void showQuizResult() {
//         double score = currentQuiz.getScore();
//         System.out.println("Your score is: " + score);
//         leaderBoard.addScore(currentUser, score);

//         System.out.println("Which Option Do You Prefer-");
//         System.out.println("1. Go Back");
//         System.out.println("2. Finish Quiz");
//         System.out.println("3. Leaderboard");
//         System.out.println("Enter the number of your choice:");

//         int choice = scanner.nextInt();
//         scanner.nextLine();  

//         switch (choice) {
//             case 1:
//                 showLoginScreen();
//                 break;
//             case 2:
//                 System.out.println("Thank You!");
//                 System.exit(0);
//                 break;
//             case 3:
//                 showLeaderboard(currentUser.getSubject());
//                 break;
//             default:
//                 System.out.println("Invalid choice. Please select either 1, 2, or 3.");
//                 showQuizResult();
//                 break;
//         }
//     }

//     private static void showLeaderboard(Subject subject) {
//         List<LeaderBoardEntry> leaderboardEntries = leaderBoard.getLeaderboard(subject);

//         System.out.println("Leaderboard for " + subject + "\n");

//         System.out.printf("%-5s %-20s %-30s %-10s\n", "Rank", "Name", "University", "Score");
//         int rank = 1;
//         for (LeaderBoardEntry entry : leaderboardEntries) {
//             User user = entry.getUser();
//             double score = entry.getScore();
//             System.out.printf("%-5d %-20s %-30s %-10.2f\n", rank++, user.getName(), user.getUniversity(), score);
//         }

//         System.out.println("\n1. Go Back");
//         System.out.println("2. Finish Quiz");
//         System.out.println("Enter the number of your choice:");

//         int choice = scanner.nextInt();
//         scanner.nextLine(); 

//         switch (choice) {
//             case 1:
//                 showLoginScreen();
//                 break;
//             case 2:
//                 System.out.println("Thank You!");
//                 System.exit(0);
//                 break;
//             default:
//                 System.out.println("Invalid choice. Please select either 1 or 2.");
//                 showLeaderboard(subject);
//                 break;
//         }
//     }

//     private static void addQuestion() {
//         System.out.println("Add a New Question\n");

//         System.out.print("Enter the question: ");
//         String questionText = scanner.nextLine();

//         String[] options = new String[4];
//         for (int i = 0; i < 4; i++) {
//             System.out.printf("Enter option %d: ", i + 1);
//             options[i] = scanner.nextLine();
//             if (options[i].isEmpty()) {
//                 System.out.println("All options must be filled out. Try again.");
//                 return;
//             }
//         }

//         int correctIndex = -1;
//         while (correctIndex < 1 || correctIndex > 4) {
//             try {
//                 System.out.print("Enter the index of the correct answer (1-4): ");
//                 correctIndex = Integer.parseInt(scanner.nextLine());
//                 if (correctIndex < 1 || correctIndex > 4) {
//                     System.out.println("Invalid index for the correct answer. Please enter a number between 1 and 4.");
//                 }
//             } catch (NumberFormatException e) {
//                 System.out.println("Invalid input. Please enter a number between 1 and 4.");
//             }
//         }

//         try {
//             Question newQuestion = new Question(questionText, options, options[correctIndex - 1], currentUser.getSubject());
//             Quiz.addQuestion(newQuestion);
//             System.out.println("Question added successfully!");

//             System.out.println("\n1. Go Back");
//             System.out.println("2. Take Quiz");
//             System.out.print("Enter the number of your choice: ");
            
//             int choice = scanner.nextInt();
//             scanner.nextLine(); 

//             switch (choice) {
//                 case 1:
//                     showOptionsScreen();
//                     break;
//                 case 2:
//                     startQuiz();
//                     break;
//                 default:
//                     System.out.println("Invalid choice. Please select either 1 or 2.");
//                     showOptionsScreen();
//                     break;
//             }
//         } catch (Exception ex) {
//             System.out.println("Error: " + ex.getMessage());
//         }
//     }
// }


