package ann.ayrapetyan.homework01;

import ann.ayrapetyan.homework01.data.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestResource {
    Question getNextQuestion();
    List<String> parseAnswers(String answersStr, String delimeter);
}