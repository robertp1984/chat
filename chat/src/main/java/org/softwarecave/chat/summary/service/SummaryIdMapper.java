package org.softwarecave.chat.summary.service;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.softwarecave.chat.summary.domain.SummaryId;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SummaryIdMapper {
    default SummaryId toSummaryId(UUID value) {
        return value == null ? null : SummaryId.of(value);
    }

    default UUID toUuid(SummaryId value) {
        return value == null ? null : value.value();
    }
}
