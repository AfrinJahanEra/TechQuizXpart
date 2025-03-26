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
        if (!hasMoreQuestions()) {
            return null;
        }
        Question question = questions.get(currentQuestionIndex);
        currentQuestionIndex++;
        return question;
    }

    public void submitAnswer(String answer) {
        if (currentQuestionIndex == 0 || currentQuestionIndex > questions.size()) {
            return;
        }
        
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
        return score; // Return as percentage
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

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < 0 || currentQuestionIndex >= questions.size()) {
            return null;
        }
        return questions.get(currentQuestionIndex);
    }
    
    public void moveToNextQuestion() {
        if (hasMoreQuestions()) {
            currentQuestionIndex++;
        }
    }
}