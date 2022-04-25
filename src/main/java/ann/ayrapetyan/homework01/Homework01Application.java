package ann.ayrapetyan.homework01;

import ann.ayrapetyan.homework01.impl.OtusTestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Homework01Application {

	public static void main(String[] args) throws IOException {
		ApplicationContext ctx = SpringApplication.run(Homework01Application.class, args);
		OtusTestService service = (OtusTestService) ctx.getBean(TestService.class);
		service.start();
	}

}
