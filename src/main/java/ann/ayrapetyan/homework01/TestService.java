package ann.ayrapetyan.homework01;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface TestService {
    void start() throws IOException;
}
