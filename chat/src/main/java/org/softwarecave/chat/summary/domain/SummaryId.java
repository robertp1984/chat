package org.softwarecave.chat.summary.domain;


import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public record SummaryId(UUID id) {
    public UUID value() {
        return id;
    }

    public static SummaryId generate() {
        return new SummaryId(UuidCreator.getTimeOrderedEpoch());
    }

    public static SummaryId of(UUID id) {
        return new SummaryId(id);
    }

}
