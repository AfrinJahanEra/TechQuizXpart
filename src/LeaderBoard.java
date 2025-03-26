package src;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
            return new ArrayList<>();
        }

        List<Map.Entry<User, Double>> leaderboard = new ArrayList<>(
            subjectLeaderboards.get(subject).entrySet()
        );
        leaderboard.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));
        return leaderboard;
    }

    private void saveLeaderboardsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            subjectLeaderboards.forEach((subject, userScores) -> {
                userScores.forEach((user, score) -> {
                    writer.println(String.join(",",
                        subject.toString(),
                        user.getName(),
                        user.getUniversity(),
                        user.getDepartment(),
                        String.valueOf(score)
                    ));
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLeaderboardsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    try {
                        Subject subject = Subject.fromString(parts[0]);
                        User user = new User(parts[1], parts[2], parts[3], subject);
                        double score = Double.parseDouble(parts[4]);
                        
                        subjectLeaderboards.putIfAbsent(subject, new HashMap<>());
                        subjectLeaderboards.get(subject).put(user, score);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Skipping invalid leaderboard entry: " + line);
                    }
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, that's fine
        }
    }
}