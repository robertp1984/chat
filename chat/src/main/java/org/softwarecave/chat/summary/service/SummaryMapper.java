package org.softwarecave.chat.summary.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.softwarecave.chat.summary.domain.Summary;
import org.softwarecave.chat.summary.entity.SummaryEntity;

@Mapper(componentModel = "spring", uses = SummaryIdMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SummaryMapper {

    Summary toSummary(SummaryEntity source);

    @Mapping(target = "version", ignore = true)
    SummaryEntity toEntity(Summary source);
}
