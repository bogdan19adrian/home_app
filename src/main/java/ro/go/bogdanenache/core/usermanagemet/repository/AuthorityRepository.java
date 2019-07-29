package ro.go.bogdanenache.core.usermanagemet.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends ReactiveCrudRepository<AuthorityRepository, String> {

}