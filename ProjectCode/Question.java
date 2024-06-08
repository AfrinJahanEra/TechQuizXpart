package ProjectCode;

public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;
    private Subject subject;

    public Question(String question, String[] options, String correctAnswer, Subject subject) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(String answer) {
        return correctAnswer.equals(answer);
    }

    public Subject getSubject() {
        return subject;
    }
}

