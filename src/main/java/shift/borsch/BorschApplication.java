package shift.borsch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

@SpringBootApplication
public class BorschApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorschApplication.class, args);
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
}