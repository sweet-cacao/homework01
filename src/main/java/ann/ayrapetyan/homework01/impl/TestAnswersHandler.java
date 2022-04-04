package ann.ayrapetyan.homework01.impl;

import ann.ayrapetyan.homework01.data.Question;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Component
public class TestAnswersHandler {
    private final List<Question> wrongQuestions = new ArrayList<>();
    private final List<Question> rightQuestions = new ArrayList<>();

    private int countWrongAnswers = 0;
    private int countRightAnswers = 0;
    private Mark mark = null;

    public void checkAnswer(Question q, List<String> answer) {
        Collections.sort(answer);
        Collections.sort(q.getRightAnswer());
        if (answer.equals(q.getRightAnswer())) {
            addRightQuestion(q);
        } else {
            addWrongQuestion(q);
        }
    }

    public void addWrongQuestion(Question q) {
        rightQuestions.add(q);
        countWrongAnswers++;
    }

    public void addRightQuestion(Question q) {
        wrongQuestions.add(q);
        countRightAnswers++;
    }

    public Mark getMark() {
        if (mark == null) {
            return mark = countMark();
        } return mark;
    }

    private Mark countMark() {
        int wholeNumQ = countRightAnswers + countWrongAnswers;
        if (countRightAnswers == 0) {
            return Mark.BAD;
        }
        float rightPercent = (float)countRightAnswers/(float)wholeNumQ * 100;
        if (rightPercent == 100) {
            return Mark.EXCELLENT;
        } else if (rightPercent >= 75) {
            return Mark.GOOD;
        } else if (rightPercent >= 45) {
            return Mark.NORMAL;
        } else {
            return Mark.BAD;
        }
    }

}

enum Mark {
    EXCELLENT(5),
    GOOD(4),
    NORMAL(3),
    BAD(2);

    Mark(int i) {
    }
}
