package ann.ayrapetyan.homework01.impl;

import ann.ayrapetyan.homework01.TestResource;
import ann.ayrapetyan.homework01.data.Question;
import ann.ayrapetyan.homework01.exception.WrongFileException;
import ann.ayrapetyan.homework01.utils.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Queue;

@Slf4j
@Component
@Scope("prototype")
public class CSVTestResource implements TestResource {

    Queue<Question> questionList;

    public CSVTestResource(@Value("${filename}") Resource res) throws WrongFileException, IOException {
        try {
            questionList = CSVUtils.parseCSVFile(res.getFile());
        } catch (IOException | WrongFileException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Question getNextQuestion() {
        return questionList.poll();
    }
}
