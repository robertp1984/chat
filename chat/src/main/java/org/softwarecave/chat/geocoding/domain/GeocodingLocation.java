package org.softwarecave.chat.geocoding.domain;

import java.io.Serializable;

public record GeocodingLocation(String name,
                                Double latitude,
                                Double longitude,
                                String country,
                                String state) implements Serializable {
}
