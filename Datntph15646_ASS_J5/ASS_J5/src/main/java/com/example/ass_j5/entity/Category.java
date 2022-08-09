package com.example.ass_j5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Cate")
@Table(name = "Category")
public class Category {
    @Id
    @Column(name = "id")
    private String id;
    @NotNull
    @NotBlank(message = "category's name cannot be null")
    @Size(min = 3, max = 300)
    @Column(name = "name")
    private String name;
    @Column(name = "updelete")
    private Integer updelete;

}
