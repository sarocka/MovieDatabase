package movie_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class MovieDbApp extends org.springframework.boot.web.support.SpringBootServletInitializer {

	public static void main(String[] args) {
		 SpringApplication.run(MovieDbApp.class, args);

	}

}
