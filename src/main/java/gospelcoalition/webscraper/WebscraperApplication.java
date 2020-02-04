package gospelcoalition.webscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class WebscraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebscraperApplication.class, args);
    }

}
