package org.softwarecave.chat.summary.service;

import org.junit.jupiter.api.Test;
import org.softwarecave.chat.summary.model.Summary;
import org.softwarecave.chat.utils.TestContainersConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TestContainersConfiguration.class)
@ActiveProfiles("test")
public class SummaryServiceIT {

    @Autowired
    private PostgreSQLContainer<?> container;

    @Autowired
    private SummaryService summaryService;

    @Test
    void testSample() {
        String text = "This is a long message that needs to be summarized.";
        String summarizedTest = "Summarized message";

        Summary summarizedMessage = summaryService.saveSummary(text,
                summarizedTest);

        assertThat(summarizedMessage)
                .isNotNull()
                .hasFieldOrPropertyWithValue("text", text)
                .hasFieldOrPropertyWithValue("textSummary", summarizedTest)
                .matches(v -> v.getId() > 0);
    }
}
