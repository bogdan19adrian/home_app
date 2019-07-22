package ro.go.bogdanenache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import com.mongodb.reactivestreams.client.MongoClient;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class BogdanHomeAppApplication {

	@Value("${spring.data.mongodb.database}")
	private String databaseName;

	@Autowired
	MongoClient mongoClient;

	public static void main(String[] args) {
		SpringApplication.run(BogdanHomeAppApplication.class, args);
	}


	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(mongoClient, databaseName);
	}


}
