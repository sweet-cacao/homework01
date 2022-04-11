package ann.ayrapetyan.homework01;

import ann.ayrapetyan.homework01.impl.OtusTestService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@ShellComponent
@Component
public class TestServiceCommands {

    private final TestService service;

    public TestServiceCommands(OtusTestService service) {
        this.service = service;
    }

    @ShellMethod("Start")
    public void startTest() throws IOException {
        service.start();
    }
}
