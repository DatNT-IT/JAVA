package com.example.duanlon.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    protected Long id;
    @Version
    protected int version;
    @NotNull
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    protected LocalDateTime createdAt;
    @NotNull
    @Column(name = "modified_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    protected LocalDateTime modifiedAt;
    protected AbstractEntity() {
        modifiedAt = LocalDateTime.now();
    }
}
