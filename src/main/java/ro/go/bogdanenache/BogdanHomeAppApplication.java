package ro.go.bogdanenache;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories
//@ComponentScan(basePackages = {"ro.go.bogdanenache.config", "ro.go.bogdanenache.security"})
public class BogdanHomeAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(BogdanHomeAppApplication.class, args);
    }



}
