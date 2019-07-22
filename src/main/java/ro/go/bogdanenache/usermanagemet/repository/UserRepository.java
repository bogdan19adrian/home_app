package ro.go.bogdanenache.usermanagemet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ro.go.bogdanenache.usermanagemet.entity.User;
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByEmail(String email);
    Mono<User> findByUsername(String username);
}


