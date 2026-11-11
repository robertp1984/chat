package org.softwarecave.chat.summary.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Summary implements Serializable {

    private SummaryId id;
    private String text;
    private String textSummary;
}
