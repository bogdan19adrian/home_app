package ro.go.bogdanenache.core.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ro.go.bogdanenache.core.usermanagemet.entity.User;
import ro.go.bogdanenache.core.usermanagemet.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@Slf4j
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    public ReactiveUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String login) {
        return userRepository.findByUsername(login)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new BadCredentialsException(String.format("User %s not found in database", login))))
                .map(this::createSpringSecurityUser);
    }

    public org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}
