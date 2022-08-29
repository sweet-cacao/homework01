package ann.ayrapetyan.homework01.impl;


import ann.ayrapetyan.homework01.TestService;
import ann.ayrapetyan.homework01.data.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@AllArgsConstructor
@Component
public class OtusTestService implements TestService {
    private CSVTestResource resource;
    private TestAnswersHandler answersHandler;

    @Override
    public void start() throws IOException {
        System.out.println("Test started");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Question q = resource.getNextQuestion();
        while(q != null) {
            System.out.println(q.getQuestion());
            q.getAnswers().forEach(System.out::println);

            System.out.println("Enter right answer/answers by space : ");
            List<String> answers = resource.parseAnswers(br.readLine(), " ");
            answersHandler.checkAnswer(q, answers);

            q = resource.getNextQuestion();
        }
        printResults();
    }

    private void printResults() {
        System.out.println("Your result is "
                + answersHandler.getMark()
                + ". Answers r/wr: "
                + answersHandler.getCountRightAnswers()
                + "/" + answersHandler.getCountWrongAnswers() + ".");
    }


}
