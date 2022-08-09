package com.example.duanlon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Evidence extends AbstractEntity{
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "criminal_case_id")
    private CriminalCase criminalCase;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storage_id")
    private Storage storage;
    @OneToMany(mappedBy = "evidence",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TrackEntry > trackEntries;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Evidence evidence = (Evidence) o;
        return Objects.equals(number, evidence.number) && Objects.equals(itemName, evidence.itemName) && Objects.equals(notes, evidence.notes) && Objects.equals(archived, evidence.archived) && Objects.equals(criminalCase, evidence.criminalCase) && Objects.equals(storage, evidence.storage) && Objects.equals(trackEntries, evidence.trackEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, itemName, notes, archived, criminalCase, storage, trackEntries);
    }
}
