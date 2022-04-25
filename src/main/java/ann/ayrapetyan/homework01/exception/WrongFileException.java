package ann.ayrapetyan.homework01.exception;

public class WrongFileException extends Exception {
    public WrongFileException() {
        super("The file has not enough information about questions and answers. " +
                "Please, use patter: question, answer answer answer, rightAnswer");
    }
}
