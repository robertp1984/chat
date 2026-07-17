package org.softwarecave.chat.weather.service.client;

import lombok.extern.slf4j.Slf4j;
import org.softwarecave.openmeteo.api.model.V1ForecastGet200Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class WeatherClient {

    private final RestClient.Builder restClientBuilder;
    private final String url;
    private final WeatherMapper weatherMapper;

    public WeatherClient(RestClient.Builder restClientBuilder,
                         @Value("${app.weather.api.url}") String url) {
        this.restClientBuilder = restClientBuilder;
        this.url = url;
        // Use WeatherMapperImpl directly because there is no point creating Spring bean for this
        this.weatherMapper = new WeatherMapperImpl();
    }

    @Cacheable(value = "weatherCurrent")
    public Weather getCurrent(double latitude, double longitude) {
        log.info("Fetching weather for coordinates: latitude={} longitude={}", latitude, longitude);
        var response = restClientBuilder
                .build()
                .get()
                .uri(url, latitude, longitude)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(V1ForecastGet200Response.class);
        return weatherMapper.toWeather(response);
    }
}
