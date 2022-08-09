package com.example.java6.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurants")

public class Restaurant implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurantId")
	private Long restaurantId;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "email", unique = true)
	private String email;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "phone")
	private String phone;

	@NotNull
	@Column(name = "gender")
	private int gender;

	@NotNull
	@Column(name = "address")
	private String address;

	@NotNull
	@Column(name = "image")
	private String image;

	@NotNull
	@Column(name = "registerDate")
	private String registerDate;

	@NotNull
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Product> products;

	@NotNull
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Category> categories;

	@NotNull
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Order> orders;
}
