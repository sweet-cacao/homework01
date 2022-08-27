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
//@Qualifier("testService")
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
            List<String> answers = parseAnswers(br.readLine(), " ");
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

    public static List<String> parseCSVFile(File file) throws WrongFileException, FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Queue<Question> getQuestionsFromLines(List<String> lines) {
        Queue<Question> questions = new LinkedList<>();
        for (String line: lines) {
            Question question = parseLine(line);
            questions.add(question);
        }
        return questions;
    }

    private static Question parseLine(String line) throws WrongFileException {
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            String question = parseString(rowScanner);
            String answersStr = parseString(rowScanner);
            List<String> answers = parseAnswers(answersStr, " ");
            String rightAnswersStr = parseString(rowScanner);
            List<String> rightAnswers = parseAnswers(rightAnswersStr, " ");
            return new Question(question, answers, rightAnswers);
        }
    }

    public static List<String> parseAnswers(String answersStr, String delimeter) {
        try (Scanner rowScanner  = new Scanner(answersStr)) {
            rowScanner.useDelimiter(delimeter);
            List<String> answers = new ArrayList<>();
            while (rowScanner.hasNext()) {
                answers.add(rowScanner.next());
            }
            return answers;
        }
    }

    private static String parseString(Scanner rowScanner) throws WrongFileException {
        if (rowScanner.hasNext()) {
            return rowScanner.next();
        } else {
            throw new WrongFileException();
        }
    }
}
