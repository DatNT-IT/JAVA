package com.example.ass_j5.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "product")
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    @NotNull
    @NotBlank(message = "Product's name cannot be null")
    @Size(min = 1, max = 300)
    private String name;

    @NotNull
    @NotBlank(message = "Product's image cannot be null")
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @NotBlank(message = "Product's title cannot be null")
    @Size(min = 1, max = 1000)
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private LocalDateTime createDate;
    @Column(name = "dateUp")
    private LocalDateTime upDate;
    @Column(name = "cate")
    private String cate;
    @Column(name = "account")
    private String account;
    @Column(name = "updelete")
    private Integer updelete;
    public Product(String id, String account, String cate, String date, String image, String name, Double price, String title, Integer updelete, String dateUp) {
        this.id = id;
        this.account = account;
        this.cate = cate;
        this.createDate = LocalDateTime.now();
        this.image = image;
        this.name = name;
        this.price = price;
        this.title = title;
        this.updelete = updelete;
        this.upDate = LocalDateTime.now();
    }
}
