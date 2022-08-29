package ann.ayrapetyan.homework01.impl;

import ann.ayrapetyan.homework01.TestResource;
import ann.ayrapetyan.homework01.data.Question;
import ann.ayrapetyan.homework01.exception.WrongFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@Scope("prototype")
public class CSVTestResource implements TestResource {

    Queue<Question> questionList;

    public CSVTestResource(@Value("${filename}") Resource res) throws WrongFileException, IOException {
        try {
            List<String> lines = parseCSVFile(res.getFile());
            questionList = getQuestionsFromLines(lines);
        } catch (IOException | WrongFileException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public List<String> parseCSVFile(File file) throws WrongFileException, FileNotFoundException {
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

    public Queue<Question> getQuestionsFromLines(List<String> lines) {
        Queue<Question> questions = new LinkedList<>();
        for (String line: lines) {
            Question question = parseLine(line);
            questions.add(question);
        }
        return questions;
    }

    private Question parseLine(String line) throws WrongFileException {
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

    public List<String> parseAnswers(String answersStr, String delimeter) {
        try (Scanner rowScanner  = new Scanner(answersStr)) {
            rowScanner.useDelimiter(delimeter);
            List<String> answers = new ArrayList<>();
            while (rowScanner.hasNext()) {
                answers.add(rowScanner.next());
            }
            return answers;
        }
    }

    private String parseString(Scanner rowScanner) throws WrongFileException {
        if (rowScanner.hasNext()) {
            return rowScanner.next();
        } else {
            throw new WrongFileException();
        }
    }

    @Override
    public Question getNextQuestion() {
        return questionList.poll();
    }
}
