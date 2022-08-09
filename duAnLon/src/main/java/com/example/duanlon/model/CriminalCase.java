package com.example.duanlon.model;

import com.example.duanlon.core.CaseStatus;
import com.example.duanlon.core.CaseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class CriminalCase extends AbstractEntity {
    @NotEmpty
    @Column(name = "case_number", unique = true, nullable = false)
    private String number;
    @NotNull
    @Column(name = "case_type")
    @Enumerated(EnumType.STRING)
    private CaseType type;
    @NotEmpty
    @Column(name = "short_description")
    private String shortDescription;
    @NotEmpty
    @Column(name = "detail_description")
    private String detailedDescription;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private CaseStatus status;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private String notes;
    @OneToMany(mappedBy = "criminalCase")
    @JsonIgnore
    @Transient
    private Set<Evidence> evidenceSet;
    @ManyToOne
    @JoinColumn(name = "LEAD_INVESTIGATOR", nullable = false)
    private Detective leadInvestigator;
    @ManyToMany
    @JoinTable(name = "working_detective_case",
            joinColumns = @JoinColumn(name = "criminal_Case_id"),
            inverseJoinColumns = @JoinColumn(name = "detective_id"))
    private Set<Detective> assigned ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CriminalCase that = (CriminalCase) o;
        return Objects.equals(number, that.number) && type == that.type && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(detailedDescription, that.detailedDescription) && status == that.status && Objects.equals(notes, that.notes) && Objects.equals(evidenceSet, that.evidenceSet) && Objects.equals(leadInvestigator, that.leadInvestigator) && Objects.equals(assigned, that.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, type, shortDescription, detailedDescription, status, notes, evidenceSet, leadInvestigator, assigned);
    }
}
