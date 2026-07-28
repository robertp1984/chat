package org.softwarecave.chat.summary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Summary implements Serializable {

    private Long id;
    private String text;
    private String textSummary;

}
