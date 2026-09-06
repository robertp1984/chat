package org.softwarecave.chat.summary.service;

import org.softwarecave.chat.summary.entity.SummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SummaryRepository extends JpaRepository<SummaryEntity, UUID> {
}
