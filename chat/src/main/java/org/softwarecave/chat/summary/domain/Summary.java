package org.softwarecave.chat.summary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Summary {

    private Long id;
    private String text;
    private String textSummary;

}
