package ann.ayrapetyan.homework01.utils;

import ann.ayrapetyan.homework01.data.Question;
import ann.ayrapetyan.homework01.exception.WrongFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CSVUtils {
    public static Queue<Question> parseCSVFile(File file) throws WrongFileException, FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            Queue<Question> questions = new LinkedList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Question question = parseLine(line);
                questions.add(question);
            }
            return questions;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
