package ProjectCode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuizTest {

    private User user;
    private Quiz quiz;

    @Before
    public void setUp() {
        user = new User("Ridika Naznin", "IUT", "CSE", Subject.OOP);
        quiz = new Quiz(user);
    }
    @Test
    public void testQuizInitialization() {
        assertEquals(0, quiz.getScore(), 0);
        assertTrue(quiz.hasMoreQuestions());
    }
    @Test
    public void testSubmitAnswer() {
        Question question = quiz.getNextQuestion();
        quiz.submitAnswer("Abstraction");
        assertEquals(1, quiz.getScore(), 0);
    }
    @Test
    public void testGetNextQuestion() {
        Question question1 = quiz.getNextQuestion();
        assertNotNull(question1);
        Question question2 = quiz.getNextQuestion();
        assertNotNull(question2);
        assertNotSame(question1, question2);
    }
}

