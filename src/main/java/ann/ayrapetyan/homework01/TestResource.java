package ann.ayrapetyan.homework01;

import ann.ayrapetyan.homework01.data.Question;
import org.springframework.stereotype.Component;

@Component
public interface TestResource {
    Question getNextQuestion();
}
