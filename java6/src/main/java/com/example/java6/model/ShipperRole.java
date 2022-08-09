package com.example.java6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipper_role") 
public class ShipperRole {
	@Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "shipperId")
    private Shipper shipper;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
