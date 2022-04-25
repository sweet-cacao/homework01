package ann.ayrapetyan.homework01.impl;

import ann.ayrapetyan.homework01.TestResource;
import ann.ayrapetyan.homework01.TestService;
import ann.ayrapetyan.homework01.data.Question;
import ann.ayrapetyan.homework01.utils.CSVUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Component
//@Qualifier("testService")
public class OtusTestService implements TestService {
    private final TestResource resource;
    private final TestAnswersHandler answersHandler;
    boolean finished = false;

    @Autowired
    public OtusTestService(CSVTestResource resource, TestAnswersHandler answersHandler) {
        this.resource = resource;
        this.answersHandler = answersHandler;
    }

    private static void accept(Question q) {
        System.out.println("Question: " + q.getQuestion());
        System.out.println("Answers: ");
        q.getAnswers().forEach(System.out::println);
        System.out.println("Right answers: ");
        q.getRightAnswer().forEach(System.out::println);
    }

    @Override
    public void start() throws IOException {
        System.out.println("Test started");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Question q = resource.getNextQuestion();
        while(q != null) {
            System.out.println(q.getQuestion());
            q.getAnswers().forEach(System.out::println);

            System.out.println("Enter right answer/answers by space : ");
            List<String> answers = CSVUtils.parseAnswers(br.readLine(), " ");
            answersHandler.checkAnswer(q, answers);

            q = resource.getNextQuestion();
        }
        finished = true;
        printResults();
    }

    @Override
    public void getAllQuestionsAndAnswers() {
        if (!finished) {
            System.out.println("Do the test at first!");
            return;
        }
        System.out.println("You were right here:");
        answersHandler.getRightQuestions().forEach(OtusTestService::accept);
        System.out.println("You failed here:");
        answersHandler.getWrongQuestions().forEach(OtusTestService::accept);
    }


    private void printResults() {
        System.out.println("Your result is "
                + answersHandler.getMark()
                + ". Answers r/wr: "
                + answersHandler.getCountRightAnswers()
                + "/" + answersHandler.getCountWrongAnswers() + ".");
    }

}
