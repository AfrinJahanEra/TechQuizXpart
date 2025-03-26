package src;


import java.util.List;

public class Quiz {
    private User user;
    private List<Question> questions;
    private int currentQuestionIndex;
    private double score;

    public Quiz(User user, List<Question> questions) {
        this.user = user;
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    public Question getNextQuestion() {
        return questions.get(currentQuestionIndex++);
    }

    public void submitAnswer(String answer) {
        if (!hasMoreQuestions()) return;
        
        Question currentQuestion = questions.get(currentQuestionIndex - 1);
        if (answer == null || answer.trim().isEmpty()) {
            return;
        }
        
        if (currentQuestion.isCorrect(answer)) {
            score += 1;
        } else {
            score -= 0.5;
        }
    }
    
    public double getScore() {
        return score;
    }
    
    public User getUser() {
        return user;
    }
    
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }
    
    public int getTotalQuestions() {
        return questions.size();
    }
}