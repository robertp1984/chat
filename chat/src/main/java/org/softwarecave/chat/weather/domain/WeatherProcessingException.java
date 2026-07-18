package org.softwarecave.chat.weather.domain;

public class WeatherProcessingException extends RuntimeException {
    public WeatherProcessingException(String message) {
        super(message);
    }

    public WeatherProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
