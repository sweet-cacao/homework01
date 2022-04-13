package ann.ayrapetyan.homework01;

import ann.ayrapetyan.homework01.impl.OtusTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
public class TestServiceCommands {

    private final TestService service;

    @Autowired
    public TestServiceCommands(OtusTestService service) {
        this.service = service;
    }

    @ShellMethod("Start task")
    public void startTest() throws IOException {
        service.start();
    }

    @ShellMethod("Get results")
    public void results() {
        service.getAllQuestionsAndAnswers();
    }
}
