package org.softwarecave.chat.weather.service.client;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.softwarecave.openmeteo.ApiClient;
import org.softwarecave.openmeteo.api.WeatherForecastApisApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
public class WeatherClient {

    private final WeatherForecastApisApi forecastApi;
    private final WeatherMapper weatherMapper;

    public WeatherClient(@Value("${app.weather.api.url}") String url,
                         RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder
                .baseUrl(url)
                .build();
        forecastApi = new WeatherForecastApisApi(new ApiClient(restClient));
        // Use WeatherMapper directly because there is no point creating Spring bean for this
        this.weatherMapper = Mappers.getMapper(WeatherMapper.class);
    }

    @Cacheable(value = "weatherCurrent")
    public Weather getCurrent(double latitude, double longitude) {
        log.info("Fetching weather for coordinates: latitude={} longitude={}", latitude, longitude);
        var response = forecastApi
                .v1ForecastGet(String.valueOf(latitude), String.valueOf(longitude), null, null,
                        List.of("temperature_2m", "wind_speed_10m", "rain"),
                        null, null, null,
                        null, null, null, null, null, null,
                        null, null, null, null,
                        null, null, null, null, null);
        return weatherMapper.toWeather(response);
    }
}
