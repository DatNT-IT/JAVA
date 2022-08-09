package com.example.duanlon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Storage extends AbstractEntity{
    private String name;
    private String location;
    @OneToMany(mappedBy = "storage")
    @JsonIgnore
    private Set<Evidence> evidenceSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Storage storage = (Storage) o;
        return Objects.equals(name, storage.name) && Objects.equals(location, storage.location) && Objects.equals(evidenceSet, storage.evidenceSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, location, evidenceSet);
    }
}
