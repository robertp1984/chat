package org.softwarecave.chat.weather.service.client;


import org.mapstruct.Mapper;
import org.softwarecave.openmeteo.api.model.V1ForecastGet200Response;

@Mapper
public interface WeatherMapper {
    Weather toWeather(V1ForecastGet200Response source);
}
