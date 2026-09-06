package org.softwarecave.chat.summary.service;

import org.mapstruct.Mapper;
import org.softwarecave.chat.summary.domain.Summary;
import org.softwarecave.chat.summary.entity.SummaryEntity;

@Mapper(uses = SummaryIdMapper.class)
public interface SummaryMapper {

    Summary toSummary(SummaryEntity source);

    SummaryEntity toEntity(Summary source);
}
