package ro.go.bogdanenache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories
public class BogdanHomeAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(BogdanHomeAppApplication.class, args);
    }


}
