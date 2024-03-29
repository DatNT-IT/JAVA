package com.example.java6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_role")

public class CustomerRole {
	@Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
