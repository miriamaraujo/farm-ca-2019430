package ie.cct.farmca2019430;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ie.cct.farmca2019430*")
public class FarmCa2019430Application {

	public static void main(String[] args) {
		SpringApplication.run(FarmCa2019430Application.class, args);
	}

}
