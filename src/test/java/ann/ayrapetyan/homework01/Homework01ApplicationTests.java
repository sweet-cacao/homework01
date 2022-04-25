package ann.ayrapetyan.homework01;

import ann.ayrapetyan.homework01.impl.OtusTestService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@SpringBootTest
class Homework01ApplicationTests {
	@Autowired
	ApplicationContext ctx;

	@Test
	void integrationTestOk_5() throws IOException {
		OtusTestService service = (OtusTestService) ctx.getBean(TestService.class);
		String userInput = "test1 test2\ntest1 test2\ntest1 test2\ntest1 test2";
		ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(bais);
		service.start();
	}


}
