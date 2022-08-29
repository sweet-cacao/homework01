package ann.ayrapetyan.homework01.impl;

import ann.ayrapetyan.homework01.TestResource;
import ann.ayrapetyan.homework01.TestService;
import ann.ayrapetyan.homework01.data.Question;
import ann.ayrapetyan.homework01.exception.WrongFileException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;


@AllArgsConstructor
@Component
public class OtusTestService implements TestService {
    private TestResource resource;
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
