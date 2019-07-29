package ro.go.bogdanenache.core.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CorsGlobalConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders("Access-Control-Allow-Headers", "*")
                .allowedHeaders("Access-Control-Allow-Origin", "*")
                .allowCredentials(true)
                .allowedMethods("PUT", "POST", "DELETE", "GET")
                .maxAge(3600);
    }
}