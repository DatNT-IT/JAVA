package com.example.java6.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="orderId")
	private Long orderId;

	@NotNull
	@Column(name = "quantity")
	private int quantity;

	@NotNull
	@Column(name = "price")
	private double price;

	@NotNull
	@Column(name = "status_shipper")
	private int status_shipper;

	@NotNull
	@Column(name = "status_customer")
	private int status_customer;

	@NotNull
	@Column(name = "status_restaurant")
	private int status_restaurant;

	@NotNull
	@Column(name = "feedback_shipper")
	private String feedback_shipper;

	@NotNull
	@Column(name = "feedback_restaurant")
	private String feedback_restaurant;

	@NotNull
	@Column(name = "orderDate")
	private String orderDate;

	@NotNull
	@Column(name = "orderDateDelivered")
	private String orderDateDelivered;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "shipperId")
	private Shipper shipper;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "restaurantId")
	private Restaurant restaurant;
	
}
