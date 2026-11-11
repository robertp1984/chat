package org.softwarecave.chat.summary.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.softwarecave.chat.summary.domain.Summary;
import org.softwarecave.chat.summary.domain.SummaryId;
import org.softwarecave.chat.utils.TestContainersConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@Import({TestContainersConfiguration.class, SummaryMapperImpl.class})
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

        SummaryId summaryId = SummaryId.generate();
        Summary summarizedMessage = summaryService.saveSummary(new Summary(summaryId, text, summarizedTest));

        assertThat(summarizedMessage)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", summaryId)
                .hasFieldOrPropertyWithValue("text", text)
                .hasFieldOrPropertyWithValue("textSummary", summarizedTest);
    }
}
