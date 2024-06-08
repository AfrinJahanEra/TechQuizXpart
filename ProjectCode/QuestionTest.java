package ProjectCode;


import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuestionTest {

    @Test
    public void testQuestionCreation() {
        String[] options = {"Option1", "Option2", "Option3", "Option4"};
        Question question = new Question("What is OOP?", options, "Option1", Subject.OOP);
        assertEquals("What is OOP?", question.getQuestion());
        assertArrayEquals(options, question.getOptions());
        assertEquals(Subject.OOP, question.getSubject());
        assertTrue(question.isCorrect("Option1"));
    }
}
