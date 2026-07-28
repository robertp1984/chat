package org.softwarecave.chat.config.security;

import lombok.Getter;

@Getter
public enum Role {
    ACTUATOR_READ("SCOPE_actuator.read"),
    ACTUATOR_WRITE("SCOPE_actuator.write"),
    SUMMARY_ALL("SCOPE_summary.all"),
    WEATHER_ALL("SCOPE_weather.all");

    private final String title;

    Role(String title) {
        this.title = title;
    }
}
