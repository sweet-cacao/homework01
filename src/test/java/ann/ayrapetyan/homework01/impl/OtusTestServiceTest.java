package ann.ayrapetyan.homework01.impl;

import ann.ayrapetyan.homework01.exception.WrongFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.when;

class OtusTestServiceTest {

    @Test
    void testOk_5() throws IOException, WrongFileException {
        testServiceStart("test1 test2\ntest1 test2\ntest1 test2\ntest1 test2", Mark.EXCELLENT);
    }

    @Test
    void testOk_4() throws IOException, WrongFileException {
        testServiceStart("test1 test2\ntest1 test2\ntest1 test2\ntest test2", Mark.GOOD);
    }

    @Test
    void testOk_3() throws IOException, WrongFileException {
        testServiceStart("test1 test2\ntest1 test2\ntest test2\ntest test2", Mark.NORMAL);
    }

    @Test
    void testOk_2() throws IOException, WrongFileException {
        testServiceStart("test1 test2\ntest test2\ntest test2\ntest test2", Mark.BAD);
    }

    private void testServiceStart(String userInput, Mark mark) throws IOException, WrongFileException {
        Resource res = Mockito.mock(Resource.class);
        when(res.getFile()).thenReturn(new File(getClass().getClassLoader().getResource("ok.csv").getFile()));
        CSVTestResource resource = new CSVTestResource(res);
        TestAnswersHandler handler = new TestAnswersHandler();
        OtusTestService service = new OtusTestService(resource, handler);
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
        service.start();
        Assertions.assertEquals(mark, handler.getMark());
    }
}