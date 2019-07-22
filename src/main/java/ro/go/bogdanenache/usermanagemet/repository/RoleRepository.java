package ro.go.bogdanenache.usermanagemet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ro.go.bogdanenache.usermanagemet.entity.Role;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, String> {

    Mono<Role> findByRole(String role);
}