package com.example.duanlon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class Person extends AbstractEntity{
    @NotNull()
    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true)
    private String username;
    @NotNull()
    @Size(min = 3, max = 30)
    @Column(nullable = false)
    private String firstName;
    @NotNull()
    @Size(min = 3, max = 30)
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @Size(min = 4, max = 50)
    @Column(nullable = false)
    private String password;
    @NotNull()
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime hiringDate;
    @Transient
    private String newPassword;
}
