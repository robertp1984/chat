package org.softwarecave.chat.summary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "summary")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SummaryEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "text")
    @NotBlank
    private String text;

    @Column(name = "text_summary")
    @NotBlank
    private String textSummary;

}
