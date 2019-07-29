package ro.go.bogdanenache.core.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ro.go.bogdanenache.core.security.ReactiveUserDetailsServiceImpl;
import ro.go.bogdanenache.core.security.dto.LoginDTO;
import ro.go.bogdanenache.core.security.jwt.JWTReactiveAuthenticationManager;
import ro.go.bogdanenache.core.security.jwt.JWTToken;
import ro.go.bogdanenache.core.security.jwt.TokenProvider;
import ro.go.bogdanenache.core.usermanagemet.entity.User;

import javax.validation.Valid;
import javax.validation.Validator;


@RestController
@RequestMapping("/authorize")
@Slf4j
public class UserSecurityController {
    private final TokenProvider tokenProvider;
    private final JWTReactiveAuthenticationManager authenticationManager;
    private final Validator validation;
    private ReactiveUserDetailsServiceImpl userDetailsService;

    public UserSecurityController(TokenProvider tokenProvider,
                                  JWTReactiveAuthenticationManager authenticationManager,
                                  Validator validation, ReactiveUserDetailsServiceImpl userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.validation = validation;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Mono<JWTToken> authorize(@Valid @RequestBody LoginDTO loginDTO) {
        if (!this.validation.validate(loginDTO).isEmpty()) {
            return Mono.error(new RuntimeException("Bad request"));
        }

        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        Mono<Authentication> authentication = this.authenticationManager.authenticate(authenticationToken);
        authentication.doOnError(throwable -> {
            throw new BadCredentialsException("Bad crendentials");
        });
        ReactiveSecurityContextHolder.withAuthentication(authenticationToken);

        return authentication.map(auth -> {
            String jwt = tokenProvider.createToken(auth);
            return new JWTToken(jwt);
        });
    }

    @PostMapping(value = "/register")
    public Mono<String> register(@Valid @RequestBody User user) {
        user.setActive(true);
        userDetailsService.createSpringSecurityUser(user);
        return Mono.just("");
    }

}
