package org.softwarecave.chat.weather.domain;

import java.io.Serializable;
import java.util.List;

public record WeatherSuggestionResponse(
        List<String> currentWeatherLines,
        List<String> locationLines,
        String clothingSuggestion
) implements Serializable {
}
