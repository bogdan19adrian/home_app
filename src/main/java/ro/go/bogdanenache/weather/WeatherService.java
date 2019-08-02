package ro.go.bogdanenache.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClientWeather;
    private final WebClient webClientCurrentWeather;
    private String baseUrl = "&units=metric&appid=f9c986f05f3b303b0afee53a585f9422";
    private String baseWeatherUrl = "http://api.openweathermap.org/data/2.5/forecast";
    private String baseCurrentWeatherUrl = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherService() {
        webClientWeather = WebClient.create(baseWeatherUrl);
        webClientCurrentWeather = WebClient.create(baseCurrentWeatherUrl);
    }

    public Mono<String> getCurrentWeather(String city) {
        return webClientCurrentWeather.get()
                .uri("?q=" + city + baseUrl)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getWeather(String city) {
        return webClientWeather.get()
                .uri("?q=" + city + baseUrl)
                .retrieve()
                .bodyToMono(String.class);
    }
}
