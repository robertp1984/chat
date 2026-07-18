package org.softwarecave.chat.summary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "summary")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SummaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    @NotBlank
    private String text;

    @Column(name = "text_summary")
    @NotBlank
    private String textSummary;

    public SummaryEntity(String text, String textSummary) {
        this(null, text, textSummary);
    }
}
