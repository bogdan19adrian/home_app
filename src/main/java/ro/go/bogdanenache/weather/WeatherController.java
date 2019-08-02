package ro.go.bogdanenache.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value = "currentWeather/{city}", method = RequestMethod.GET)
    public Mono<String> getCurrentWeather(@PathVariable String city) {
        return weatherService.getCurrentWeather(city);
    }

    @RequestMapping(value = "weather/{city}", method = RequestMethod.GET)
    public Mono<String> getWeather(@PathVariable String city) {
        return weatherService.getWeather(city);
    }
}
