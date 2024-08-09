package bg.softuni.onlinepharmacynews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlinePharmacyNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinePharmacyNewsApplication.class, args);
	}

}
