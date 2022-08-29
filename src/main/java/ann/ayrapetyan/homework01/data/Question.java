package ann.ayrapetyan.homework01.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Question {
    private String question;
    private List<String> answers;
    private List<String> rightAnswer;
}
