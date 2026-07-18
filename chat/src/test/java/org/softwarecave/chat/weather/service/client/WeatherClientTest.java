package org.softwarecave.chat.weather.service.client;

import org.junit.jupiter.api.Test;
import org.softwarecave.openmeteo.api.model.V1ForecastGet200Response;
import org.softwarecave.openmeteo.api.model.V1ForecastGet200ResponseCurrent;
import org.softwarecave.openmeteo.api.model.V1ForecastGet200ResponseCurrentUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(WeatherClient.class)
public class WeatherClientTest {

    public static final double LATITUDE = 52.2297;
    public static final double LONGITUDE = 21.0122;

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private JsonMapper jsonMapper;

    @Test
    void testGetCurrentWeather() {
        mockServer
                .expect(requestTo(containsString("forecast")))
                .andRespond(withSuccess().body(getSampleForecastResponse()).contentType(MediaType.APPLICATION_JSON));

        var weather = weatherClient.getCurrent(LATITUDE, LONGITUDE);

        assertThat(weather).isNotNull();
        assertThat(weather.current())
                .hasFieldOrPropertyWithValue("temperature2m", 25.0)
                .hasFieldOrPropertyWithValue("rain", 5.0)
                .hasFieldOrPropertyWithValue("windSpeed10m", 3.0);
    }

    private String getSampleForecastResponse() {
        var weather = new V1ForecastGet200Response()
                .currentUnits(new V1ForecastGet200ResponseCurrentUnits()
                        .rain("mm")
                        .temperature2m("C")
                        .windSpeed10m("m/s"))
                .current(new V1ForecastGet200ResponseCurrent()
                        .temperature2m(25.0f)
                        .rain(5.0f)
                        .windSpeed10m(3.0f));
        return jsonMapper.writeValueAsString(weather);
    }
}
