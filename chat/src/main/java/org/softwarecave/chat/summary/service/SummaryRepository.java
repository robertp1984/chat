package org.softwarecave.chat.summary.service;

import java.util.UUID;
import org.softwarecave.chat.summary.entity.SummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<SummaryEntity, UUID> {}
