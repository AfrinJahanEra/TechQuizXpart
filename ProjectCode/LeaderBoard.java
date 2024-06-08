package ProjectCode;

import java.io.*;
import java.util.*;

public class LeaderBoard {
    private Map<Subject, Map<User, Double>> subjectLeaderboards = new HashMap<>();
    private static final String FILE_PATH = "leaderboard_data.txt";

    public LeaderBoard() {
        loadLeaderboardsFromFile();
    }

    public void addScore(User user, double score) {
        subjectLeaderboards.putIfAbsent(user.getSubject(), new HashMap<>());
        subjectLeaderboards.get(user.getSubject()).put(user, score);
        saveLeaderboardsToFile();
    }


    public List<Map.Entry<User, Double>> getLeaderboard(Subject subject) {
        if (!subjectLeaderboards.containsKey(subject)) {
            return new ArrayList<>();//This conditional statement checks if the subjectLeaderboards map contains the specified subject as a key.
            //If the subject is not found in the subjectLeaderboards map, the method returns an empty ArrayList.
        }

        //sorting in map(key user, value their score)
        List<Map.Entry<User, Double>> leaderboard = new ArrayList<>(subjectLeaderboards.get(subject).entrySet());
        leaderboard.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));
        return leaderboard;
    }



    private void saveLeaderboardsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Subject subject : subjectLeaderboards.keySet()) {
                for (Map.Entry<User, Double> entry : subjectLeaderboards.get(subject).entrySet()) {
                    User user = entry.getKey();
                    double score = entry.getValue();
                    writer.println(subject + "," + user.getName() + "," + user.getUniversity() + "," + user.getDepartment() + "," + score);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    private void loadLeaderboardsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    Subject subject = Subject.fromString(parts[0]);
                    String name = parts[1];
                    String university = parts[2];
                    String department = parts[3];
                    double score = Double.parseDouble(parts[4]);
                    User user = new User(name, university, department, subject);
                    subjectLeaderboards.putIfAbsent(subject, new HashMap<>());
                    subjectLeaderboards.get(subject).put(user, score);
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid subject: " + parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

