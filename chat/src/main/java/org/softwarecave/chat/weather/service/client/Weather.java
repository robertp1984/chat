package org.softwarecave.chat.weather.service.client;

import java.io.Serializable;

public record Weather(CurrentUnits currentUnits,
                      Current current)
        implements Serializable {

    public record CurrentUnits(
            String temperature2m,
            String rain,
            String windSpeed10m) implements Serializable {
    }

    public record Current(
            Double temperature2m,
            Double rain,
            Double windSpeed10m) implements Serializable {
    }
}
