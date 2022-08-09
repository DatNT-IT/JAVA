package com.example.java6.model;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="productId")
	private Long productId;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "image")
	private String image;

	@NotNull
	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "price")
	private double price;

	@NotNull
	@Column(name = "status")
	private int status;

	@NotNull
	@Column(name = "orderQuantity")
	private int orderQuantity;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	private Restaurant restaurant;
	
	
}
