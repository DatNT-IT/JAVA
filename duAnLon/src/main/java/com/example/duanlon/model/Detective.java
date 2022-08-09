package com.example.duanlon.model;

import com.example.duanlon.core.EmploymentStatus;
import com.example.duanlon.core.Rank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Detective extends AbstractEntity{
    @NotNull
    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String badgeNumber;
    @Enumerated(EnumType.STRING)
    private Rank rank;
    private Boolean armed = false;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus status = EmploymentStatus.ACTIVE;
    @ManyToMany(mappedBy = "assigned",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CriminalCase> criminalCases;
    @OneToMany(mappedBy = "detective")
    @JsonIgnore
    private Set<TrackEntry> trackEntries;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Detective detective = (Detective) o;
        return Objects.equals(person, detective.person) && Objects.equals(badgeNumber, detective.badgeNumber) && rank == detective.rank && Objects.equals(armed, detective.armed) && status == detective.status && Objects.equals(criminalCases, detective.criminalCases) && Objects.equals(trackEntries, detective.trackEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person, badgeNumber, rank, armed, status, criminalCases, trackEntries);
    }
}
