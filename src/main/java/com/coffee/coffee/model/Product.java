package com.coffee.coffee.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @DecimalMin("0.0")
    private Double price;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotBlank(message = "Image is required")
    private String image;

    @NotBlank(message = "Description is required")
    private String description;

    public String getImagePath() {
        if (image == null) {
            return null;
        }
        return "/img/" + image;
    }
}
